package util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.List;
import java.util.stream.IntStream;

import static java.time.Duration.*;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class Helpers {

    public enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private final PointerInput FINGER = new PointerInput(TOUCH, "finger");


    public void swipeByDirection(AndroidDriver driver, Directions directions) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        int startY= (int) (height/1.5);
        int startX= width/2;
        int endY=startY;
        int endX=startX;

        switch (directions) {
            case UP -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);
            case DOWN -> {endY = (int) (driver.manage().window().getSize().getHeight() * 0.9); startY = height/2;}
            case LEFT -> { endX= (int) (driver.manage().window().getSize().getWidth() * 0.1); startX = (int) (width* 0.9);}
            case RIGHT -> { endX = (int) (driver.manage().window().getSize().getWidth() * 0.9); startX = (int)(width *0.1);}
            default -> throw new IllegalArgumentException("Invalid direction selected:" + directions);
        }

        Sequence swipe = new Sequence(FINGER, 0);
        swipe.addAction(FINGER.createPointerMove(ZERO, viewport(), startX, startY));
        swipe.addAction(FINGER.createPointerDown(LEFT.asArg()));
        swipe.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), endX, endY));
        swipe.addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(List.of(swipe));
    }

    public void scrollTo(AndroidDriver driver, WebElement element, Directions directions, int scrollCount) {
        int attempts = 0;
        boolean elementFound = false;
        while (attempts < scrollCount && !elementFound) {
            try {
                // Check if the element is visible
                if (element.isDisplayed()) {
                    elementFound = true;
                    break;
                }
            } catch (Exception ignored) {
            }
            swipeByDirection(driver, directions);
            attempts++;
        }
        if (!elementFound) {
            throw new NoSuchElementException("Element not found after scrolling " + scrollCount + " times.");
        }
    }


    public void swipeTo(AndroidDriver driver, WebElement element,Directions directions,int swipeCount){
        int attempts = 0;
        boolean elementFound = false;
        while (attempts < swipeCount && !elementFound) {
            try {
                // Check if the element is visible
                if (element.isDisplayed()) {
                    elementFound = true;
                    break;
                }
            } catch (Exception ignored) {
            }
            swipeByDirection(driver, directions);
            attempts++;
        }
        if (!elementFound) {
            throw new NoSuchElementException("Element not found after swiping " + swipeCount + " times.");
        }
    }

    public void swipeDownElementByCoordinates(AndroidDriver driver, WebElement element){
        Point location = getCenter(element);
        int height = (int) (driver.manage().window().getSize().getHeight()*0.9);
        Sequence swiping = new Sequence(FINGER, 0);
        swiping.addAction(FINGER.createPointerMove(ZERO, viewport(), location.x, location.y));
        swiping.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swiping.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), location.x, height));
        swiping.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swiping));
    }

    public void longPress(AndroidDriver driver, WebElement element) {
        Point location = getCenter(element);
        Sequence longPress = new Sequence(FINGER, 0);
        longPress.addAction(FINGER.createPointerMove(ZERO, viewport(), location.x, location.y));
        longPress.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), location.x, location.y));
        longPress.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(longPress));
    }

    public Point getCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
    }
    public void pressOutsideSlider(AndroidDriver driver, WebElement element) {
        Point location = getCenter(element);
        Sequence longPress = new Sequence(FINGER, 0);
        longPress.addAction(FINGER.createPointerMove(ZERO, viewport(), location.x, (int) ((location.y)*0.9)));
        longPress.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(longPress));
    }

}
