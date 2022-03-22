package elong.models

class Product {
    private var maSp:String=""
    private var tenSp:String=""
    private var donGia:Double=0.0
    constructor()
    constructor(maSp:String,tenSp:String,donGia:Double){
        this.maSp = maSp
        this.tenSp = tenSp
        this.donGia = donGia
    }
    public var MaSp:String
    get(){return this.maSp}
    set(value) {maSp = value}
    public var TenSp:String
    get(){return this.tenSp}
    set(value) {tenSp = value}
    public var DonGia:Double
    get(){return this.donGia}
    set(value) {donGia = value}
}