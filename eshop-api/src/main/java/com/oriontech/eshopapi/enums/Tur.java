package com.oriontech.eshopapi.enums;


public enum Tur {
	INEK("İnek"), DUVE("Düve"), DANA("Dana"), BUZAGI("Buzağı"), BOGA("Boğa"), MANDA("Manda"),
	KOYUN("Koyun"), TOKLU("Toklu"), KOC("Koç"), KECI("Keçi"), OGLAK("Oğlak"), TEKE("Teke"), TAVUK("Tavuk"), HOROZ("Horoz"),HINDI("Hindi"),DIGER("Diğer");

	public final String tur;

	Tur(String tur) {
		this.tur = tur;
	}

	
	
	public String getTur() {
		return tur;
	}



	@Override
	public String toString() {
		return tur;
	}
	

}
