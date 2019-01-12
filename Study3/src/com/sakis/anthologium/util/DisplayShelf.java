package com.sakis.anthologium.util;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

@SuppressWarnings("unused")
public class DisplayShelf extends Region {
	private final Duration DURATION = Duration.millis(500);
	private final Interpolator INTERPOLATOR = Interpolator.EASE_BOTH;
	private final double SPACING = 100;
	private final double LEFT_OFFSET = -110;
	private final double RIGHT_OFFSET = 110;
	private final double SCALE_SMALL = 0.7;
	private PerspectiveImage[] items;
	private Group centered = new Group();
	private Group left = new Group();
	private Group center = new Group();
	private Group right = new Group();
	private int centerIndex = 0;
	private Timeline timeline;
	private ScrollBar scrollBar = new ScrollBar();
	private boolean localChange = false;
	private Rectangle clip = new Rectangle();

	/**
	 * Constructor, needs an array of images 
	 * @param images
	 */
	public DisplayShelf(Image[] images) 
	{
		// set clip
		setClip(clip);

		// set id's for css
		setId("displayshelf");
		scrollBar.setId("display-scrollbar");

		// create items
		items = new PerspectiveImage[images.length];
		for (int i = 0; i < images.length; i++) {
			final PerspectiveImage item = items[i] = new PerspectiveImage(images[i]);
			final double index = i;
			item.setOnMouseClicked((MouseEvent me) -> {
				localChange = true;
				scrollBar.setValue(index);
				localChange = false;
				shiftToCenter(item);
			});
		}

		// setup scrollbar
		scrollBar.setMax(items.length - 1);
		scrollBar.setVisibleAmount(1);
		scrollBar.setUnitIncrement(1);
		scrollBar.setBlockIncrement(1);
		scrollBar.valueProperty().addListener((Observable ov) -> {
			if (!localChange) {
				shiftToCenter(items[(int) Math.round(scrollBar.getValue())]);
			}
		});

		// create content
		centered.getChildren().addAll(left, right, center);
		getChildren().addAll(centered, scrollBar);

		// listen for keyboard events
		setFocusTraversable(true);
		setOnKeyPressed((KeyEvent ke) -> {
			if (ke.getCode() == KeyCode.LEFT) {
				shift(1);
				localChange = true;
				scrollBar.setValue(centerIndex);
				localChange = false;
			} else if (ke.getCode() == KeyCode.RIGHT) {
				shift(-1);
				localChange = true;
				scrollBar.setValue(centerIndex);
				localChange = false;
			}
		});

		// update
		update();
	}

	@Override
	protected void layoutChildren() 
	{
		// update clip to our size
		clip.setWidth(getWidth());
		clip.setHeight(getHeight());

		// keep centered centered
		centered.setLayoutY((getHeight() - PerspectiveImage.HEIGHT) / 2);
		centered.setLayoutX((getWidth() - PerspectiveImage.WIDTH) / 2);

		// position scroll bar at bottom
		scrollBar.setLayoutX(10);
		scrollBar.setLayoutY(getHeight() - 25);
		scrollBar.resize(getWidth() - 20, 15);
	}

	private void update() 
	{
		// move items to new homes in groups
		left.getChildren().clear();
		center.getChildren().clear();
		right.getChildren().clear();
		for (int i = 0; i < centerIndex; i++) {
			left.getChildren().add(items[i]);
		}
		center.getChildren().add(items[centerIndex]);

		for (int i = items.length - 1; i > centerIndex; i--) {
			right.getChildren().add(items[i]);
		}

		// stop old time if there is one running
		if (timeline != null) {
			timeline.stop();
		}

		// create timeline to animate new positions
		timeline = new Timeline();

		// add keyframes for left items
		final ObservableList<KeyFrame> keyFrames = timeline.getKeyFrames();
		for (int i = 0; i < left.getChildren().size(); i++) {
			final PerspectiveImage it = items[i];
			double newX = -left.getChildren().size() * SPACING + SPACING * i + LEFT_OFFSET;
			keyFrames.add(new KeyFrame(DURATION, new KeyValue(it.translateXProperty(), newX, INTERPOLATOR),
					new KeyValue(it.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
					new KeyValue(it.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),
					new KeyValue(it.angle, 45.0, INTERPOLATOR)));
		}

		// add keyframe for center item
		final PerspectiveImage centerItem = items[centerIndex];
		keyFrames.add(new KeyFrame(DURATION, new KeyValue(centerItem.translateXProperty(), 0, INTERPOLATOR),
				new KeyValue(centerItem.scaleXProperty(), 1.0, INTERPOLATOR),
				new KeyValue(centerItem.scaleYProperty(), 1.0, INTERPOLATOR),
				new KeyValue(centerItem.angle, 90.0, INTERPOLATOR)));

		// add keyframes for right items
		for (int i = 0; i < right.getChildren().size(); i++) {
			final PerspectiveImage it = items[items.length - i - 1];
			final double newX = right.getChildren().size() * SPACING - SPACING * i + RIGHT_OFFSET;
			keyFrames.add(new KeyFrame(DURATION, new KeyValue(it.translateXProperty(), newX, INTERPOLATOR),
					new KeyValue(it.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
					new KeyValue(it.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),
					new KeyValue(it.angle, 135.0, INTERPOLATOR)));
		}

		// play animation
		timeline.play();
	}

	private void shiftToCenter(PerspectiveImage item) 
	{
		for (int i = 0; i < left.getChildren().size(); i++) {
			if (left.getChildren().get(i) == item) {
				int shiftAmount = left.getChildren().size() - i;
				shift(shiftAmount);
				return;
			}
		}
		if (center.getChildren().get(0) == item) {
			return;
		}
		for (int i = 0; i < right.getChildren().size(); i++) {
			if (right.getChildren().get(i) == item) {
				int shiftAmount = -(right.getChildren().size() - i);
				shift(shiftAmount);
				return;
			}
		}
	}

	private void shift(int shiftAmount) 
	{
		if (centerIndex <= 0 && shiftAmount > 0) {
			return;
		}
		if (centerIndex >= items.length - 1 && shiftAmount < 0) {
			return;
		}
		centerIndex -= shiftAmount;
		update();
	}

	/**
	 * A node that displays an image with some 2.5D perspective rotation around the
	 * y axis.
	 * 
	 * @author Isaak Tosounidis
	 *
	 */
	private class PerspectiveImage extends Parent {
		private static final double REFLECTION_SIZE = 0.25;
		public static final double WIDTH = 200;
		public static final double HEIGHT = WIDTH + (WIDTH * REFLECTION_SIZE);
		private static final double RADIUS_H = WIDTH / 2;
		private static final double BACK = WIDTH / 10;

		private PerspectiveTransform transform = new PerspectiveTransform();

		/**
		 * Angle property
		 */
		public final DoubleProperty angle = new SimpleDoubleProperty(45) {
			@Override
			protected void invalidated() {
				// when angle changes calculate new transform
				double lx = (RADIUS_H - Math.sin(Math.toRadians(angle.get())) * RADIUS_H - 1);
				double rx = (RADIUS_H + Math.sin(Math.toRadians(angle.get())) * RADIUS_H + 1);
				double uly = (-Math.cos(Math.toRadians(angle.get())) * BACK);
				double ury = -uly;
				transform.setUlx(lx);
				transform.setUly(uly);
				transform.setUrx(rx);
				transform.setUry(ury);
				transform.setLrx(rx);
				transform.setLry(HEIGHT + uly);
				transform.setLlx(lx);
				transform.setLly(HEIGHT + ury);
			}
		};

		public final double getAngle() {
			return angle.getValue();
		}

		public final void setAngle(double value) {
			angle.setValue(value);
		}

		public final DoubleProperty angleModel() {
			return angle;
		}

		public PerspectiveImage(Image image) {
			ImageView imageView = new ImageView(image);
			Reflection reflection = new Reflection();
			reflection.setFraction(REFLECTION_SIZE);
			imageView.setEffect(reflection);
			setEffect(transform);
			getChildren().addAll(imageView);
		}

	}
}
