import com.codeborne.selenide.*;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@ExtendWith(AllureJunit5.class)
public class TestSuite {

    @BeforeEach
    public void setUp() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://kamil-demo.alpinizm.uz";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Test
    @Feature("Filters form")
    @Story("Check Filters form")
    @Description("Check entry fields, checkboxes, minimum and maximum values, Amenities checkboxes, “Clear all” is functional, check buttons without clicking 'Apply'")
    public void checkFiltersForm() {
        open("/");
		
        // Click on the Search button
        $("#search-button").click();
        // Click on the Filter button
        $("#filter-button").click();

        // Check entry fields
        $("#entry-field").shouldBe(visible);

        // Check checkboxes
        $("#checkbox").shouldBe(selected);

        // Check minimum and maximum values
        $("#min-value").shouldHave(value("min"));
        $("#max-value").shouldHave(value("max"));

        // Check Amenities checkboxes
        $("#amenities-checkbox").shouldBe(selected);

        // Check “Clear all” is functional
        $("#clear-all").click();
        $("#checkbox").shouldNotBe(selected);
        $("#amenities-checkbox").shouldNotBe(selected);

        // Check buttons without clicking "Apply"
        $("#apply-button").shouldNotBe(clicked);
    }

    @Test
    @Feature("All listings page")
    @Story("Check 'All listings' page has the same amount of listings as the 'All' label")
    @Description("Check that the 'All listings' page (https://kamil-demo.alpinizm.uz/all-listings) has the same amount of listings as the 'All' label")
    public void checkAllListingsPage() {
        open("/all-listings");
        int listingsCount = $$(".listing").size();
        int allLabelCount = Integer.parseInt($("#all-label").getText());
        Assertions.assertEquals(listingsCount, allLabelCount);
    }
}
