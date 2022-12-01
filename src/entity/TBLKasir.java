/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class TBLKasir {
    private String kodeKsr;
    private String namaKsr;
    private String passwordKsr;

    public TBLKasir() {
    }

    public TBLKasir(String kodeKsr, String namaKsr, String passwordKsr) {
        this.kodeKsr = kodeKsr;
        this.namaKsr = namaKsr;
        this.passwordKsr = passwordKsr;
    }

    public String getKodeKsr() {
        return kodeKsr;
    }

    public String getNamaKsr() {
        return namaKsr;
    }

    public String getPasswordKsr() {
        return passwordKsr;
    }
    
    
}
