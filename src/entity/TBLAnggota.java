package entity;

public class TBLAnggota {
	private String noAnggota;
	private String nama;
	private Integer wajib;
	private Integer pokok;
	private Integer saldo;
	
	public TBLAnggota() {}

	public TBLAnggota(final Builder builder) {
		this.noAnggota = builder.noAnggota;
		this.nama = builder.nama;
		this.wajib = builder.wajib;
		this.pokok = builder.pokok;
		this.saldo = builder.saldo;
	}

	public String getNoAnggota() {
		return noAnggota;
	}

	public String getNama() {
		return nama;
	}

	public Integer getWajib() {
		return wajib;
	}

	public Integer getPokok() {
		return pokok;
	}

	public Integer getSaldo() {
		return saldo;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private String noAnggota;
		private String nama;
		private Integer wajib;
		private Integer pokok;
		private Integer saldo;
		
		public Builder noAnggota(final String noAnggota) {
			this.noAnggota = noAnggota;
			return this;
		}
		
		public Builder nama(final String nama) {
			this.nama = nama;
			return this;
		}
		
		public Builder wajib(final Integer wajib) {
			this.wajib = wajib;
			return this;
		}
		
		public Builder pokok(final Integer pokok) {
			this.pokok = pokok;
			return this;
		}
		
		public Builder saldo(final Integer saldo) {
			this.saldo = saldo;
			return this;
		}
		
		public TBLAnggota build() {
			return new TBLAnggota(this);
		}
	}
	
}
