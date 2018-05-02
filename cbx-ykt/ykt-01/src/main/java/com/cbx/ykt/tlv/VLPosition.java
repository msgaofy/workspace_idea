package com.cbx.ykt.tlv;

public class VLPosition {
    private int _vl;
    private int _vp;

    public VLPosition(int _vl, int _vp) {
        this._vl = _vl;
        this._vp = _vp;
    }

    public int get_vl() {
        return _vl;
    }

    public void set_vl(int _vl) {
        this._vl = _vl;
    }

    public int get_vp() {
        return _vp;
    }

    public void set_vp(int _vp) {
        this._vp = _vp;
    }
}
