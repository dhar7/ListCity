package com.example.simpleparadox.listycity;

import android.app.Activity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import org.junit.After; import org.junit.Before;
import org.junit.Rule; import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test class for MainActivity. All the UI tests are written here. Robotium test framework is
 used
 */
@RunWith(AndroidJUnit4.class)

public class ShowActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }
   /*
    @Test
    public void switchActivity(){

        ListView listView = (ListView) solo.getView(R.id.city_list);
        View view = listView.getChildAt(0);
        solo.clickOnView(view);
        solo.assertCurrentActivity("Failed to load ShowActivity", ShowActivity.class);

    }

    @Test
    public void checkCityName(){
        ListView listView = (ListView) solo.getView(R.id.city_list);
        View view = listView.getChildAt(0);
        solo.clickOnView(view);
        TextView text = (TextView) solo.getView(R.id.cityname);
        String name = text.getText().toString();
        assertEquals("Edmonton", name);
    }

    @Test
    public void checkButton1(){
        ListView listView = (ListView) solo.getView(R.id.city_list);
        View view = listView.getChildAt(0);
        solo.clickOnView(view);
        solo.waitForActivity(ShowActivity.class);
        Button button = (Button) solo. getView(R.id.back);
        solo.clickOnView(button);
        solo.assertCurrentActivity("Failed to return MainActivity", MainActivity.class);
    } */
    @Test
    public void switchActivity(){
        solo.clickOnButton("ADD CITY"); //Click ADD CITY Button

        //Get view for EditText and enter a city name
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM"); //Select CONFIRM Button

        solo.clickOnText("Edmonton");
        solo.assertCurrentActivity("Failed to load ShowActivity", ShowActivity.class);
        assertTrue(solo.waitForText("Edmonton", 1, 2000));
        solo.clickOnButton("Back"); //Select ClEAR ALL
        solo.assertCurrentActivity("Failed to return MainActivity", MainActivity.class);
        assertTrue(solo.waitForText("Edmonton", 1, 2000));
    }
    /**
     * Close activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception{
        solo.waitForActivity("showActivityTest", 2000);
        solo.finishOpenedActivities();

    }

}
