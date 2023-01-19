
package com.openclassrooms.entrevoisins.neighbour_list;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import android.app.Activity;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ClickNeighbourViewAction;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test class for list of favorite neighbours
 */
@RunWith(AndroidJUnit4.class)
public class FavNeighboursListTest {

    private Activity mActivity;

    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * When we click on neighbour, show about neighbour
     */
    @Test
    public void myNeighbour_click_aboutNeighbour() {
        // Click on first neighbour
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickNeighbourViewAction()));
        // Checked launched activity
        onView(ViewMatchers.withId(R.id.tv_about)).check(matches(ViewMatchers.withText(R.string.a_propos_de_moi)));
    }

    /**
     * When we add a neighbour, this one is added to favorite list
     */
    @Test
    public void myNeighbour_checked_favoriteList() {
        // Switch in favorite list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollRight());
        // Check if favorite list is empty
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(0));
        // Switch in neighbour list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollLeft());
        // Click on first neighbour
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickNeighbourViewAction()));
        // Checked launched activity
        onView(ViewMatchers.withId(R.id.tv_about)).check(matches(ViewMatchers.withText(R.string.a_propos_de_moi)));
        // Click on star button to add in favorite list
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
        // Return in neighbour list
        pressBack();
        // Check if show neighbour list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // Switch in favorite list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollRight());
        // Check if show favorite list
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(matches(isDisplayed()));
        // Check if favorite list contains one neighbour
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(1));
        // Check if in favorite list, name of neighbour is full
        onView(ViewMatchers.withId(R.id.item_fav_list_name)).check(matches(isDisplayed()));
    }

    /**
     * When we delete neighbour in favorite list, this one is removed to favorite list
     */

    @Test
    public void myNeighbour_removed_favoriteList() {
        // Click on first neighbour
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickNeighbourViewAction()));
        // Checked launched activity
        onView(ViewMatchers.withId(R.id.tv_about)).check(matches(ViewMatchers.withText(R.string.a_propos_de_moi)));
        // Click on star button to add in favorite list
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
        // Return in neighbour list
        pressBack();
        // Check if show neighbour list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // Switch in favorite list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollToPage(2));
        // Check if neighbour is added to favorite list
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(1));
        // Click on delete button
        onView(ViewMatchers.withId(R.id.item_fav_list_delete_button)).perform(click());
        // Check if neighbour is removed
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(0));
    }

    /**
     * When we switch to favorite list, show favorite neighbour
     */

    @Test
    public void neighboursList_and_favoritesList() {
        // Check all neighbour in neighbours list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(12));
        // Switch in favorite list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollRight());
        // Check if favorite list is empty
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(0));
        // Switch in neighbour list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollLeft());
        // Check if show neighbour list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // Click on neighbour
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickNeighbourViewAction()));
        // Add in favorite list
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
        // Return in neighbour list
        pressBack();
        // Check if show neighbour list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // Switch in favorite list
        onView(ViewMatchers.withId(R.id.container)).perform(ViewPagerActions.scrollToPage(2));
        // Check is favorite list contains favorite neighbour
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(1));
        // Remove neighbour to favorite
        onView(ViewMatchers.withId(R.id.item_fav_list_delete_button)).perform(click());
        // Check if favorite list is empty
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(0));
    }
}