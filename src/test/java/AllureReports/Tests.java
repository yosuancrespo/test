package AllureReports;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.Configuration;

@ExtendWith(AllureJunit5.class)
public class Tests {

    @BeforeEach
    public void setUp() {
    	Configuration.browserSize = "1920x1080";
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
    $(".iIKBzI.sc-giAqHp").click();
    
    // Click on the Check-In field
    $(".WzEhh.sc-eSoXWK > .hTuscY.sc-euEtCV > .blYCdH.sc-gsWcmt > .gQYBcx.sc-eGJWMs > .jqmzTW.sc-csTbgd").click();
    
    // Select initial date (December 25th, 2023)
    $("div:nth-of-type(1) > .jLeSfx.sc-WZYut > div:nth-of-type(37)").click();
    
    // Select end date (December 29th, 2023)
    $("div:nth-of-type(1) > .jLeSfx.sc-WZYut > div:nth-of-type(41)").click();
    
    // Click on the Filter button
    $(".eaGTVv.sc-giAqHp").click();
   
    // Enter minimum price
    $("div:nth-of-type(1) > .sc-gstuGz.tcgHY > .htFMXJ.sc-kmIPcE > .gcBpdI.sc-dkuGKe").setValue("50");
    
    // Enter maximum price
    $("div:nth-of-type(2) > .sc-gstuGz.tcgHY > .htFMXJ.sc-kmIPcE > .gcBpdI.sc-dkuGKe").setValue("120");
    
    // Increase the amount of beds
    $("div:nth-of-type(4) > .jHMcWv.sc-ljpcbl > .fKwyEY.sc-gzcbmu > .ckwDLe.eEVTIr.sc-flUlpA.sc-iGkqmO").click();
    
    // Decrease the amount of beds
    $("div:nth-of-type(4) > .jHMcWv.sc-ljpcbl > .fKwyEY.sc-gzcbmu > .eEVTIr.kTTKRj.sc-eXuyPJ.sc-flUlpA").click();
   
    // Increase the amount of bedrooms
    $("div:nth-of-type(5) > .jHMcWv.sc-ljpcbl > .fKwyEY.sc-gzcbmu > .ckwDLe.eEVTIr.sc-flUlpA.sc-iGkqmO").click();
    
    // Decrease the amount of bedrooms
    $("div:nth-of-type(5) > .jHMcWv.sc-ljpcbl > .fKwyEY.sc-gzcbmu > .eEVTIr.kTTKRj.sc-eXuyPJ.sc-flUlpA").click();
   
    // Increase the amount of bathrooms
    $("div:nth-of-type(6) > .jHMcWv.sc-ljpcbl > .fKwyEY.sc-gzcbmu > .ckwDLe.eEVTIr.sc-flUlpA.sc-iGkqmO").click();
   
    // Decrease the amount of bathrooms
    $("div:nth-of-type(6) > .jHMcWv.sc-ljpcbl > .fKwyEY.sc-gzcbmu > .eEVTIr.kTTKRj.sc-eXuyPJ.sc-flUlpA").click();
    
    // Check Amenities checkboxes
    $("div:nth-of-type(1) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Beach front
    $("div:nth-of-type(2) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Swimming pool
    $("div:nth-of-type(3) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Free WiFi
    $("div:nth-of-type(4) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Kitchen
    $("div:nth-of-type(5) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Air conditioning
    $("div:nth-of-type(6) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Washing Machine
    $("div:nth-of-type(7) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Pets allowed
    $("div:nth-of-type(8) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Hot tub
    $("div:nth-of-type(9) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Street parking
    $("div:nth-of-type(10) > .jrzUMQ.sc-gVFcvn > .fepHIR.sc-fWWYYk").click(); // Suitable for children
   
    // Check “Clear all” is functional
    $("b").click();
    }

    @Test
    @Feature("All listings page")
    @Story("Check 'All listings' page")
    @Description("Check that the 'All listings' page (https://kamil-demo.alpinizm.uz/all-listings) has the same amount of listings as the 'All' label")
    public void checkListingsPage() {
    open("/all-listings");
    int listingsCount;

    // Keep scrolling until no more new listings load
    while (true) {
        // Get the current number of listings
        int oldCount = $$("div > .dUffpk.sc-QxirK").size();

        // Scroll to the bottom of the page
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");

        // Wait for the listings to load
        Selenide.sleep(5000); 

        // Get the new number of listings
        listingsCount = $$("div > .dUffpk.sc-QxirK").size();

        // If the count didn't increase, break the loop
        if (listingsCount == oldCount) {
            break;
        }
    }

    String allLabelText = $(".hYJCa.sc-bsatvv").getText();
    int allLabelCount = Integer.parseInt(allLabelText.replaceAll("[^0-9]", ""));
    Assertions.assertEquals(listingsCount, allLabelCount);
    }

}
