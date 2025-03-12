import android.content.Intent
import androidx.test.espresso.IdlingRegistry
import com.rwothoromo.developers.util.EspressoIdlingResource
import com.rwothoromo.developers.view.GithubUserProfile
import com.rwothoromo.developers.view.MainActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Mock MainActivity test.
 */
class GithubUserListMockTest {

    /**
     * Mock MainActivity test rule.
     */
//    @Rule
//    @JvmField
//    var mActivityScenario: ActivityScenario<MainActivity> = object : ActivityScenario<MainActivity>(MainActivity::class.java, true, true) {
//
//        override fun getActivityIntent(): Intent {
//            val targetContext = InstrumentationRegistry.getTargetContext()
//            return Intent(targetContext, MainActivity::class.java)
//        }
//    }

    private var server: MockWebServer? = null

    /**
     * Set up the server.
     *
     * @throws Exception if fails
     */
    @Before
    @Throws(Exception::class)
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

        // Instantiate a MockWebServer.
        server = MockWebServer()

        // Schedule some responses.
        val fileName = "mock_api_200_response.json"
//        server!!.enqueue(MockResponse()
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .addHeader("Cache-Control", "no-cache")
//                .setResponseCode(200)
//                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().context,
//                        fileName)))

        // Start the server.
//        server!!.start()

        // Ask the server for its URL
        //        BASE_URL = server.url("/").toString();
    }

    /**
     * Check for Clickable RecyclerView items.
     *
     * @throws Exception
     */
//    @Test
//    fun testClickableRecyclerViewItem() {
//
//        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
//
//        Intents.init()
//
//        // Scroll to an item at a position and click on it.
//        val mockPosition = 0
//        onView(withId(R.id.recyclerView)).perform(
//                actionOnItemAtPosition<RecyclerView.ViewHolder>(mockPosition, click()))
//
//        intended(allOf<Intent>(hasComponent(GithubUserProfile::class.java.name)))
//
//        Intents.release()
//    }

    /**
     * Tear down tests.
     *
     * @throws Exception if fails
     */
    @After
    @Throws(Exception::class)
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
        server!!.shutdown()
    }
}
