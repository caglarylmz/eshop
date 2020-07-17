package com.oriontech.eshopapi.enums;

public enum Yas {
	BILINMIYOR("Bilinmiyor"), AY0_3("0-3 aylık"), AY3_6("3-6 Aylık"), AY6_12("6-12 Aylık"), YAS1("1"), YAS2("2"),
	YAS3("3"), YAS4("4"), YAS5("5"), YAS6("6"), YAS7("7"), YAS8("8"), YAS9("9"), YAS10("10 ve Üzeri");

	public final String yas;

	Yas(String yas) {
		this.yas = yas;
	}
	
	public String getYas() {
		return yas;
	}



	@Override
	public String toString() {
		return yas;
	}

}
