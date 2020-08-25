package techproedturkish01.techproedturkish01api;

import org.codehaus.jackson.annotate.JsonProperty;

public class Bookingdates {
	
	/*
	 POJO da olmasi gerekenler
	                1) JSON'da key olanlar icin variable olusturun ve variablelerin 
	                   access modifiritni privte yapin
	                2) Her variable icin get ve set olusturun 
	                3) Parametresiz contsructor olusturun ve super i silin
	                4) Parametreli consttructor olusturun ve superi silin
	                5) toString methodu olusturun
	 */

	@JsonProperty("checkin")
	private String checkin;
	@JsonProperty("checkout")
	private String checkout;
	
	@JsonProperty("checkin")
	public String getCheckin() {
		return checkin;
	}

	@JsonProperty("checkin")
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	@JsonProperty("checkout")
	public String getCheckout() {
		return checkout;
	}

	@JsonProperty("checkout")
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	

	
	public Bookingdates(String checkin, String checkout) {
		
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Bookingdates() {
		
	}

	@Override
	public String toString() {
		return "Bookingdates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}

}