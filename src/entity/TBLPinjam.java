package entity;

import java.util.Date;

public class TBLPinjam {
	private String noPinjam;
	private Date tanggal;
	private String noAnggota;
	private Integer jumlahPinjam;
	private String kodeKasir;
	
	public TBLPinjam() {}

	public TBLPinjam(final Builder builder) {
		this.noPinjam = builder.noPinjam;
		this.tanggal = builder.tanggal;
		this.noAnggota = builder.noAnggota;
		this.jumlahPinjam = builder.jumlahPinjam;
		this.kodeKasir = builder.kodeKasir;
	}
	
	public String getNoPinjam() {
		return noPinjam;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public String getNoAnggota() {
		return noAnggota;
	}
	public Integer getJumlahPinjam() {
		return jumlahPinjam;
	}
	public String getKodeKasir() {
		return kodeKasir;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private String noPinjam;
		private Date tanggal;
		private String noAnggota;
		private Integer jumlahPinjam;
		private String kodeKasir;
		
		public Builder noPinjam(final String noPinjam) {
			this.noPinjam = noPinjam;
			return this;
		}
		
		public Builder tanggal(final Date tanggal) {
			this.tanggal = tanggal;
			return this;
		}
		
		public Builder noAnggota(final String noAnggota) {
			this.noAnggota = noAnggota;
			return this;
		}
		
		public Builder jumlahPinjam(final Integer jumlahPinjam) {
			this.jumlahPinjam = jumlahPinjam;
			return this;
		}
		
		public Builder kodeKasir(final String kodeKasir) {
			this.kodeKasir = kodeKasir;
			return this;
		}
		
		public TBLPinjam build() {
			return new TBLPinjam(this);
		}
	}

}
