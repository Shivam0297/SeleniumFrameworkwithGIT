package pageEvents;

import pageObject.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
	
	ElementFetch ele = new ElementFetch();
	public void signinButton()
	{
		ele.getWebElements("XPATH", HomePageElements.signinButtonText).click();
	}

}
