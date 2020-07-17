package com.oriontech.eshopapi.enums;


public enum Irk {
	SIMENTAL("Simental"), HOLSTEIN("Holstein"), MONTOFON("Montofon"), JERSEY("Jersey"), YERLI("Yerli"), GUNEYSARISI("Güney Sarısı"), KIRMA("Kırma"),DIGER("Diğer");

	public final String irk;

	Irk(String irk) {
		this.irk = irk;
	}


	public String getIrk() {
		return irk;
	}




	@Override
	public String toString() {
		return irk;
	}

}
