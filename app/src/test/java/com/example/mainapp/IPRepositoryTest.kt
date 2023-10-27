import com.example.findmyip.Network.IPAPI
import com.example.findmyip.Network.IPRepositoryImpl
import com.example.findmyip.model.IPResponseModel
import com.example.findmyip.model.UISTATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

@ExperimentalCoroutinesApi

class IPRepositoryTest {

    @Mock
    private lateinit var mockService: IPAPI

    private lateinit var repository: IPRepositoryImpl
    private val mockResponse = IPResponseModel(
        ip = "185.202.220.234",
        network = "185.202.220.0/24",
        version = "IPv4",
        city = "New York",
        region = "New York",
        regionCode = "NY",
        country = "US",
        countryName = "United States",
        countryCode = "US",
        countryCodeISO3 = "USA",
        countryCapital = "Washington",
        countryTLD = ".us",
        continentCode = "NA",
        inEU = false,
        postal = "10019",
        latitude = 40.762321,
        longitude = -73.982183,
        timezone = "America/New_York",
        utcOffset = "-0400",
        countryCallingCode = "+1",
        currency = "USD",
        currencyName = "Dollar",
        languages = "en-US,es-US,haw,fr",
        countryArea = 9629091.0,
        countryPopulation = 327167434,
        asn = "AS141039",
        org = "Packethub s.a."
    )

    @Before
    fun setUp() {
        mockService = mock(IPAPI::class.java)
        repository = IPRepositoryImpl(mockService)


    }

    @Test
    fun `getIP should return UISTATESUCCESS when the request is successful and the body is not empty`() {
        runBlocking {
            `when`(mockService.getIP()).thenReturn(Response.success(mockResponse))

            val job = launch {
                repository.getIP()
                    .flowOn(Dispatchers.IO)
                    .collect { state ->
                        if (state is UISTATE.SUCCESS) {
                            // cancel job when success state has been reached
                            cancel()
                        }
                    }
            }

            // Wait for the success state to be reached
            delay(1000)

            // Assert that the coroutine has been cancelled
            assertTrue(job.isCancelled)
        }
    }

}

