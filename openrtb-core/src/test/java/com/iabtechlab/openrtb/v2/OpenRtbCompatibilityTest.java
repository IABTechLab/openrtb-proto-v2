package com.iabtechlab.openrtb.v2;

import com.google.protobuf.InvalidProtocolBufferException;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OpenRtbCompatibilityTest {

    @Test
    public void googleAndIabMessagesAreEqualAfterSerialization() {
        assertArrayEquals(generateGoogleBidRequest().toByteArray(), generateIabBidRequest().toByteArray());
    }

    @Test
    public void googleMessagesCanBeDeserializedByIad() throws InvalidProtocolBufferException {
        com.google.openrtb.OpenRtb.BidRequest googleGeneratedBidRequest = generateGoogleBidRequest();

        OpenRtb.BidRequest iabParsedBidRequest = OpenRtb.BidRequest.parseFrom(googleGeneratedBidRequest.toByteArray());

        assertEquals(generateIabBidRequest(), iabParsedBidRequest);
    }

    @Test
    public void iabMessagesCanBeDeserializedByGoogle() throws InvalidProtocolBufferException {
        OpenRtb.BidRequest iabGeneratedBidRequest = generateIabBidRequest();

        com.google.openrtb.OpenRtb.BidRequest googleParsedBidRequest = com.google.openrtb.OpenRtb.BidRequest.parseFrom(iabGeneratedBidRequest.toByteArray());

        assertEquals(generateGoogleBidRequest(), googleParsedBidRequest);
    }

    private static com.google.openrtb.OpenRtb.BidRequest generateGoogleBidRequest() {
        com.google.openrtb.OpenRtb.BidRequest.Imp.Builder imp1 =
                com.google.openrtb.OpenRtb.BidRequest.Imp.newBuilder()
                                                         .setId("imp1")
                                                         .setBanner(com.google.openrtb.OpenRtb.BidRequest.Imp.Banner.newBuilder()
                                                                                                                    .setWmax(300)
                                                                                                                    .setWmin(200)
                                                                                                                    .setHmax(100)
                                                                                                                    .setHmin(50)
                                                                                                                    .setId("banner1")
                                                                                                                    .setPos(com.google.openrtb.OpenRtb.AdPosition.ABOVE_THE_FOLD)
                                                                                                                    .addBtype(com.google.openrtb.OpenRtb.BannerAdType.JAVASCRIPT_AD)
                                                                                                                    .addBattr(com.google.openrtb.OpenRtb.CreativeAttribute.TEXT_ONLY)
                                                                                                                    .addMimes("image/gif")
                                                                                                                    .setTopframe(true)
                                                                                                                    .addExpdir(com.google.openrtb.OpenRtb.ExpandableDirection.RIGHT)
                                                                                                                    .addApi(com.google.openrtb.OpenRtb.APIFramework.MRAID_1))
                                                         .setDisplaymanager("dm1")
                                                         .setDisplaymanagerver("1.0")
                                                         .setInstl(false)
                                                         .setTagid("tag1")
                                                         .setBidfloor(100.0)
                                                         .setBidfloorcur("USD")
                                                         .setSecure(false)
                                                         .addIframebuster("buster1")
                                                         .setPmp(com.google.openrtb.OpenRtb.BidRequest.Imp.Pmp.newBuilder()
                                                                                                              .setPrivateAuction(false)
                                                                                                              .addDeals(com.google.openrtb.OpenRtb.BidRequest.Imp.Pmp.Deal.newBuilder()
                                                                                                                                                                          .setId("deal1")
                                                                                                                                                                          .setBidfloor(200.0)
                                                                                                                                                                          .setBidfloorcur("USD")
                                                                                                                                                                          .addWseat("seat2")
                                                                                                                                                                          .addWadomain("goodadv1")
                                                                                                                                                                          .setAt(com.google.openrtb.OpenRtb.AuctionType.SECOND_PRICE)));

        @SuppressWarnings("deprecation")
        com.google.openrtb.OpenRtb.BidRequest.Imp.Builder imp2 =
                com.google.openrtb.OpenRtb.BidRequest.Imp.newBuilder()
                                                         .setId("imp2")
                                                         .setVideo(com.google.openrtb.OpenRtb.BidRequest.Imp.Video.newBuilder()
                                                                                                                  // Video/Audio common
                                                                                                                  .addMimes("video/vp9")
                                                                                                                  .setMinduration(15)
                                                                                                                  .setMaxduration(60)
                                                                                                                  .addProtocols(com.google.openrtb.OpenRtb.Protocol.VAST_2_0)
                                                                                                                  .setStartdelay(0)
                                                                                                                  .setSequence(1)
                                                                                                                  .addBattr(com.google.openrtb.OpenRtb.CreativeAttribute.TEXT_ONLY)
                                                                                                                  .setMaxextended(120)
                                                                                                                  .setMinbitrate(1000)
                                                                                                                  .setMaxbitrate(2000)
                                                                                                                  .addDelivery(com.google.openrtb.OpenRtb.ContentDeliveryMethod.STREAMING)
                                                                                                                  .addCompanionad(com.google.openrtb.OpenRtb.BidRequest.Imp.Banner.newBuilder()
                                                                                                                                                                                  .setId("compad1")
                                                                                                                                                                                  .setW(100)
                                                                                                                                                                                  .setH(50))
                                                                                                                  .addApi(com.google.openrtb.OpenRtb.APIFramework.VPAID_2)
                                                                                                                  .addCompaniontype(com.google.openrtb.OpenRtb.CompanionType.HTML)
                                                                                                                  // Video specific
                                                                                                                  .setLinearity(com.google.openrtb.OpenRtb.VideoLinearity.LINEAR)
                                                                                                                  .setProtocol(com.google.openrtb.OpenRtb.Protocol.VAST_3_0)
                                                                                                                  .setW(200)
                                                                                                                  .setH(50)
                                                                                                                  .setBoxingallowed(false)
                                                                                                                  .addPlaybackmethod(com.google.openrtb.OpenRtb.PlaybackMethod.CLICK_TO_PLAY)
                                                                                                                  .setPos(com.google.openrtb.OpenRtb.AdPosition.ABOVE_THE_FOLD)
                                                                                                                  .setCompanionad21(com.google.openrtb.OpenRtb.BidRequest.Imp.Video.CompanionAd.newBuilder()
                                                                                                                                                                                               .addBanner(com.google.openrtb.OpenRtb.BidRequest.Imp.Banner.newBuilder()
                                                                                                                                                                                                                                                          .setId("compad2")
                                                                                                                                                                                                                                                          .setW(110)
                                                                                                                                                                                                                                                          .setH(60))));

        com.google.openrtb.OpenRtb.BidRequest.Imp.Native.Builder nativ = com.google.openrtb.OpenRtb.BidRequest.Imp.Native.newBuilder()
                                                                                                                         .setVer("1.0")
                                                                                                                         .addApi(com.google.openrtb.OpenRtb.APIFramework.MRAID_1)
                                                                                                                         .addBattr(com.google.openrtb.OpenRtb.CreativeAttribute.TEXT_ONLY);
        nativ.setRequestNative(generateGoogleNativeRequest());

        com.google.openrtb.OpenRtb.BidRequest.Imp.Builder imp3 =
                com.google.openrtb.OpenRtb.BidRequest.Imp.newBuilder()
                                                         .setId("imp3")
                                                         .setNative(nativ);

        com.google.openrtb.OpenRtb.BidRequest.Device.Builder device =
                com.google.openrtb.OpenRtb.BidRequest.Device.newBuilder()
                                                            .setUa("Chrome")
                                                            .setGeo(com.google.openrtb.OpenRtb.BidRequest.Geo.newBuilder()
                                                                                                             .setLat(90.0)
                                                                                                             .setLon(45.0)
                                                                                                             .setType(com.google.openrtb.OpenRtb.LocationType.GPS_LOCATION)
                                                                                                             .setCountry("USA")
                                                                                                             .setRegion("New York")
                                                                                                             .setRegionfips104("US36")
                                                                                                             .setMetro("New York")
                                                                                                             .setCity("New York City")
                                                                                                             .setZip("10000")
                                                                                                             .setUtcoffset(3600))
                                                            .setDnt(false)
                                                            .setLmt(false)
                                                            .setIp("192.168.1.0")
                                                            .setIpv6("1:2:3:4:5:6:0:0")
                                                            .setDevicetype(com.google.openrtb.OpenRtb.DeviceType.MOBILE)
                                                            .setMake("Motorola")
                                                            .setModel("MotoX")
                                                            .setOs("Android")
                                                            .setOsv("3.2.1")
                                                            .setHwv("X")
                                                            .setW(640)
                                                            .setH(1024)
                                                            .setPpi(300)
                                                            .setPxratio(1.0)
                                                            .setJs(true)
                                                            .setFlashver("11")
                                                            .setLanguage("en")
                                                            .setCarrier("77777")
                                                            .setConnectiontype(com.google.openrtb.OpenRtb.ConnectionType.CELL_4G)
                                                            .setIfa("999")
                                                            .setDidsha1("1234")
                                                            .setDidmd5("4321")
                                                            .setDpidsha1("5678")
                                                            .setDpidmd5("8765")
                                                            .setMacsha1("abc")
                                                            .setMacmd5("xyz");

        com.google.openrtb.OpenRtb.BidRequest.User.Builder user =
                com.google.openrtb.OpenRtb.BidRequest.User.newBuilder()
                                                          .setId("user1")
                                                          .setBuyeruid("Picard")
                                                          .setYob(1973)
                                                          .setGender("M")
                                                          .setKeywords("boldly,going")
                                                          .setCustomdata("data1")
                                                          .setGeo(com.google.openrtb.OpenRtb.BidRequest.Geo.newBuilder().setZip("12345"))
                                                          .addData(com.google.openrtb.OpenRtb.BidRequest.Data.newBuilder()
                                                                                                             .setId("data1")
                                                                                                             .setName("dataname1")
                                                                                                             .addSegment(com.google.openrtb.OpenRtb.BidRequest.Data.Segment.newBuilder()
                                                                                                                                                                           .setId("seg1")
                                                                                                                                                                           .setName("segname1")
                                                                                                                                                                           .setValue("segval1")));

        com.google.openrtb.OpenRtb.BidRequest.Regs.Builder regs =
                com.google.openrtb.OpenRtb.BidRequest.Regs.newBuilder()
                                                          .setCoppa(true);

        com.google.openrtb.OpenRtb.BidRequest.Builder bidRequest =
                com.google.openrtb.OpenRtb.BidRequest.newBuilder()
                                                     .setId("9zj61whbdl319sjgz098lpys5cngmtro_full_true_true")
                                                     .addImp(imp1)
                                                     .addImp(imp2)
                                                     .addImp(imp3)
                                                     .setDevice(device)
                                                     .setUser(user)
                                                     .setAt(com.google.openrtb.OpenRtb.AuctionType.SECOND_PRICE)
                                                     .setTmax(100)
                                                     .addWseat("seat1")
                                                     .setAllimps(false)
                                                     .addCur("USD")
                                                     .addAllBcat(asList("IAB11", "IAB11-4"))
                                                     .addBadv("badguy")
                                                     .setRegs(regs)
                                                     .setTest(false);

        return bidRequest.build();
    }

    private static OpenRtb.BidRequest generateIabBidRequest() {
        @SuppressWarnings("deprecation")
        OpenRtb.BidRequest.Imp.Builder imp1 = OpenRtb.BidRequest.Imp.newBuilder()
                                                                    .setId("imp1")
                                                                    .setBanner(OpenRtb.BidRequest.Imp.Banner.newBuilder()
                                                                                                            .setWmax(300)
                                                                                                            .setWmin(200)
                                                                                                            .setHmax(100)
                                                                                                            .setHmin(50)
                                                                                                            .setId("banner1")
                                                                                                            .setPos(OpenRtb.AdPosition.ABOVE_THE_FOLD.getNumber())
                                                                                                            .addBtype(OpenRtb.BannerAdType.JAVASCRIPT_AD.getNumber())
                                                                                                            .addBattr(OpenRtb.CreativeAttribute.TEXT_ONLY.getNumber())
                                                                                                            .addMimes("image/gif")
                                                                                                            .setTopframe(true)
                                                                                                            .addExpdir(OpenRtb.ExpandableDirection.RIGHT.getNumber())
                                                                                                            .addApi(OpenRtb.APIFramework.MRAID_1.getNumber()))
                                                                    .setDisplaymanager("dm1")
                                                                    .setDisplaymanagerver("1.0")
                                                                    .setInstl(false)
                                                                    .setTagid("tag1")
                                                                    .setBidfloor(100.0)
                                                                    .setBidfloorcur("USD")
                                                                    .setSecure(false)
                                                                    .addIframebuster("buster1")
                                                                    .setPmp(OpenRtb.BidRequest.Imp.Pmp.newBuilder()
                                                                                                      .setPrivateAuction(false)
                                                                                                      .addDeals(OpenRtb.BidRequest.Imp.Pmp.Deal.newBuilder()
                                                                                                                                               .setId("deal1")
                                                                                                                                               .setBidfloor(200.0)
                                                                                                                                               .setBidfloorcur("USD")
                                                                                                                                               .addWseat("seat2")
                                                                                                                                               .addWadomain("goodadv1")
                                                                                                                                               .setAt(OpenRtb.AuctionType.SECOND_PRICE.getNumber())));

        @SuppressWarnings("deprecation")
        OpenRtb.BidRequest.Imp.Builder imp2 = OpenRtb.BidRequest.Imp.newBuilder()
                                                                    .setId("imp2")
                                                                    .setVideo(OpenRtb.BidRequest.Imp.Video.newBuilder()
                                                                                                          // Video/Audio common
                                                                                                          .addMimes("video/vp9")
                                                                                                          .setMinduration(15)
                                                                                                          .setMaxduration(60)
                                                                                                          .addProtocols(OpenRtb.Protocol.VAST_2_0.getNumber())
                                                                                                          .setStartdelay(0)
                                                                                                          .setSequence(1)
                                                                                                          .addBattr(OpenRtb.CreativeAttribute.TEXT_ONLY.getNumber())
                                                                                                          .setMaxextended(120)
                                                                                                          .setMinbitrate(1000)
                                                                                                          .setMaxbitrate(2000)
                                                                                                          .addDelivery(OpenRtb.ContentDeliveryMethod.STREAMING.getNumber())
                                                                                                          .addCompanionad(OpenRtb.BidRequest.Imp.Banner.newBuilder()
                                                                                                                                                       .setId("compad1")
                                                                                                                                                       .setW(100)
                                                                                                                                                       .setH(50))
                                                                                                          .addApi(OpenRtb.APIFramework.VPAID_2.getNumber())
                                                                                                          .addCompaniontype(OpenRtb.CompanionType.HTML.getNumber())
                                                                                                          // Video specific
                                                                                                          .setLinearity(OpenRtb.VideoLinearity.LINEAR.getNumber())
                                                                                                          .setProtocol(OpenRtb.Protocol.VAST_3_0.getNumber())
                                                                                                          .setW(200)
                                                                                                          .setH(50)
                                                                                                          .setBoxingallowed(false)
                                                                                                          .addPlaybackmethod(OpenRtb.PlaybackMethod.CLICK_TO_PLAY.getNumber())
                                                                                                          .setPos(OpenRtb.AdPosition.ABOVE_THE_FOLD.getNumber())
                                                                                                          .setCompanionad21(OpenRtb.BidRequest.Imp.Video.CompanionAd.newBuilder()
                                                                                                                                                                    .addBanner(OpenRtb.BidRequest.Imp.Banner.newBuilder()
                                                                                                                                                                                                            .setId("compad2")
                                                                                                                                                                                                            .setW(110)
                                                                                                                                                                                                            .setH(60))));

        OpenRtb.BidRequest.Imp.Native.Builder nativ = OpenRtb.BidRequest.Imp.Native.newBuilder()
                                                                                   .setVer("1.0")
                                                                                   .addApi(OpenRtb.APIFramework.MRAID_1.getNumber())
                                                                                   .addBattr(OpenRtb.CreativeAttribute.TEXT_ONLY.getNumber());
        nativ.setRequestNative(generateIabNativeRequest());

        OpenRtb.BidRequest.Imp.Builder imp3 = OpenRtb.BidRequest.Imp.newBuilder()
                                                                    .setId("imp3")
                                                                    .setNative(nativ);

        OpenRtb.BidRequest.Device.Builder device = OpenRtb.BidRequest.Device.newBuilder()
                                                                            .setUa("Chrome")
                                                                            .setGeo(OpenRtb.BidRequest.Geo.newBuilder()
                                                                                                          .setLat(90.0)
                                                                                                          .setLon(45.0)
                                                                                                          .setType(OpenRtb.LocationType.GPS_LOCATION.getNumber())
                                                                                                          .setCountry("USA")
                                                                                                          .setRegion("New York")
                                                                                                          .setRegionfips104("US36")
                                                                                                          .setMetro("New York")
                                                                                                          .setCity("New York City")
                                                                                                          .setZip("10000")
                                                                                                          .setUtcoffset(3600))
                                                                            .setDnt(false)
                                                                            .setLmt(false)
                                                                            .setIp("192.168.1.0")
                                                                            .setIpv6("1:2:3:4:5:6:0:0")
                                                                            .setDevicetype(OpenRtb.DeviceType.MOBILE.getNumber())
                                                                            .setMake("Motorola")
                                                                            .setModel("MotoX")
                                                                            .setOs("Android")
                                                                            .setOsv("3.2.1")
                                                                            .setHwv("X")
                                                                            .setW(640)
                                                                            .setH(1024)
                                                                            .setPpi(300)
                                                                            .setPxratio(1.0)
                                                                            .setJs(true)
                                                                            .setFlashver("11")
                                                                            .setLanguage("en")
                                                                            .setCarrier("77777")
                                                                            .setConnectiontype(OpenRtb.ConnectionType.CELL_4G.getNumber())
                                                                            .setIfa("999")
                                                                            .setDidsha1("1234")
                                                                            .setDidmd5("4321")
                                                                            .setDpidsha1("5678")
                                                                            .setDpidmd5("8765")
                                                                            .setMacsha1("abc")
                                                                            .setMacmd5("xyz");

        OpenRtb.BidRequest.User.Builder user = OpenRtb.BidRequest.User.newBuilder()
                                                                      .setId("user1")
                                                                      .setBuyeruid("Picard")
                                                                      .setYob(1973)
                                                                      .setGender("M")
                                                                      .setKeywords("boldly,going")
                                                                      .setCustomdata("data1")
                                                                      .setGeo(OpenRtb.BidRequest.Geo.newBuilder().setZip("12345"))
                                                                      .addData(OpenRtb.BidRequest.Data.newBuilder()
                                                                                                      .setId("data1")
                                                                                                      .setName("dataname1")
                                                                                                      .addSegment(OpenRtb.BidRequest.Data.Segment.newBuilder()
                                                                                                                                                 .setId("seg1")
                                                                                                                                                 .setName("segname1")
                                                                                                                                                 .setValue("segval1")));

        OpenRtb.BidRequest.Regs.Builder regs = OpenRtb.BidRequest.Regs.newBuilder()
                                                                      .setCoppa(true);

        OpenRtb.BidRequest.Builder bidRequest = OpenRtb.BidRequest.newBuilder()
                                                                  .setId("9zj61whbdl319sjgz098lpys5cngmtro_full_true_true")
                                                                  .addImp(imp1)
                                                                  .addImp(imp2)
                                                                  .addImp(imp3)
                                                                  .setDevice(device)
                                                                  .setUser(user)
                                                                  .setAt(OpenRtb.AuctionType.SECOND_PRICE.getNumber())
                                                                  .setTmax(100)
                                                                  .addWseat("seat1")
                                                                  .setAllimps(false)
                                                                  .addCur("USD")
                                                                  .addAllBcat(asList("IAB11", "IAB11-4"))
                                                                  .addBadv("badguy")
                                                                  .setRegs(regs)
                                                                  .setTest(false);

        return bidRequest.build();
    }

    private static com.google.openrtb.OpenRtb.NativeRequest.Builder generateGoogleNativeRequest() {
        return com.google.openrtb.OpenRtb.NativeRequest.newBuilder()
                                                       .setVer("1")
                                                       .setLayout(com.google.openrtb.OpenRtb.LayoutId.APP_WALL)
                                                       .setAdunit(com.google.openrtb.OpenRtb.AdUnitId.IAB_IN_AD_NATIVE)
                                                       .setPlcmtcnt(1)
                                                       .setSeq(1);
    }

    private static OpenRtb.NativeRequest.Builder generateIabNativeRequest() {
        return OpenRtb.NativeRequest.newBuilder()
                                    .setVer("1")
                                    .setLayout(OpenRtb.LayoutId.APP_WALL.getNumber())
                                    .setAdunit(OpenRtb.AdUnitId.IAB_IN_AD_NATIVE.getNumber())
                                    .setPlcmtcnt(1)
                                    .setSeq(1);
    }
}
