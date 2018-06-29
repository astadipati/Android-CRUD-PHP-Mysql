package net.ramastudio.myretro.model;

public class ResponseModel {
//    disini kita akan buat pojo untuk respons modelnya
//    harus sama dengan nilai pesan api

    String kode, pesan;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

//    setelah ini kita bisa buat api Interfacenya untuk handle insert
}
