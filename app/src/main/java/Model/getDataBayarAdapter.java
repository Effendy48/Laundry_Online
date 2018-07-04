package Model;

/**
 * Created by vbast on 04/07/18.
 */

public class getDataBayarAdapter {
String kd_transaksi,kd_member,alamat,tgl_transaksi,berat,total;

    public void setKd_transaksi(String kd_transaksi) {
        this.kd_transaksi = kd_transaksi;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public void setKd_member(String kd_member) {
        this.kd_member = kd_member;
    }

    public void setTgl_transaksi(String tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKd_transaksi() {
        return kd_transaksi;
    }

    public String getTotal() {
        return total;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getBerat() {
        return berat;
    }

    public String getKd_member() {
        return kd_member;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }
}
