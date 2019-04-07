var t = function() {
    wx.getLocation({
        success: function(t) {
            var n = t.latitude, o = t.longitude;
            wx.setStorageSync("position", {
                lat: n,
                lng: o
            });
        },
        fail: function(t) {
            wx.setStorageSync("position", {
                lat: "34.61866",
                lng: "112.46403"
            });
        }
    });
};

module.exports = {
    position: function n() {
        var o = wx.getStorageSync("position");
        return "position".length ? o : (t(), n());
    },
    update: t,
    choose: function(t, n, o, e) {
        wx.chooseLocation({
            latitude: t,
            longitude: n,
            success: function(t) {
                wx.setStorageSync(o + "_cn", {
                    name: t.name,
                    address: t.address,
                    lat: t.latitude,
                    lng: t.longitude
                }), "function" == typeof e && e();
            },
            fail: function() {
                wx.setStorageSync(o + "_cn", {
                    name: "获取失败",
                    address: "获取失败，请开启定位"
                });
            },
            complete: function() {}
        });
    },
    key: "position"
};