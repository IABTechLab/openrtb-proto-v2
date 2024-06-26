/*
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iabtechlab.openrtb.v2.util;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import com.iabtechlab.openrtb.v2.OpenRtb.BidRequest;
import com.iabtechlab.openrtb.v2.OpenRtb.BidRequest.Imp;
import com.iabtechlab.openrtb.v2.OpenRtb.BidRequest.Imp.Banner;
import com.iabtechlab.openrtb.v2.Test.Test1;
import com.iabtechlab.openrtb.v2.Test.Test2;
import com.iabtechlab.openrtb.v2.TestExt;
import com.iabtechlab.openrtb.v3.Enums.AuctionType;
import org.junit.Test;

/**
 * Tests for {@link ProtoUtils}.
 */
public class ProtoUtilsTest {

  @Test
  public void testFilter() {
    Test1 test1 = Test1.newBuilder().setTest1("test1").build();
    Test2 test2 = Test2.newBuilder().setTest2("test2").build();
    BidRequest reqExt = BidRequest.newBuilder()
        .setId("0")
        .addImp(Imp.newBuilder()
            .setId("1")
            .setBanner(Banner.newBuilder()
                .setExtension(TestExt.testBanner, test1)))
            // No extension on Imp, must recurse anyway
        .setExtension(TestExt.testRequest1, test1)
        .setExtension(TestExt.testRequest2, test2)
        .build();
    assertThat(ProtoUtils.filter(reqExt, true, fd -> true))
        .isSameInstanceAs(reqExt);
    assertThat(ProtoUtils.filter(reqExt, true, fd -> false)).isNull();
    assertThat(ProtoUtils.filter(reqExt, false, fd -> false))
        .isSameInstanceAs(BidRequest.getDefaultInstance());

    BidRequest reqPlainClear = BidRequest.newBuilder()
        .setId("0")
        .addImp(Imp.newBuilder().setId("1"))
        .build();
    assertThat(ProtoUtils.filter(reqExt, true, ProtoUtils.NOT_EXTENSION)).isEqualTo(reqPlainClear);

    BidRequest reqPlainNoClear = BidRequest.newBuilder()
        .setId("0")
        .addImp(Imp.newBuilder().setId("1")
            .setBanner(Banner.newBuilder()))
        .build();
    assertThat(ProtoUtils.filter(reqExt, false, ProtoUtils.NOT_EXTENSION))
        .isEqualTo(reqPlainNoClear);

    BidRequest reqExtNoImp = reqExt.toBuilder().clearImp().build();
    assertThat(ProtoUtils.filter(reqExt, false, fd -> !"imp".equals(fd.getName())))
        .isEqualTo(reqExtNoImp);

    BidRequest reqDiff = reqPlainClear.toBuilder().setId("1").build();
    ImmutableList<BidRequest> list = ImmutableList.of(reqPlainClear, reqDiff);
    assertThat(ProtoUtils.filter(list, req -> "0".equals(req.getId())))
        .containsExactly(reqPlainClear);
    assertThat(ProtoUtils.filter(list, req -> "0".equals(req.getId())))
        .containsExactly(reqPlainClear);
    assertThat(ProtoUtils.filter(list, req -> true)).isSameInstanceAs(list);
    assertThat(ProtoUtils.filter(list, req -> false)).isEmpty();
  }

  @Test
  public void testUpdate() {
    BidRequest.Builder req = BidRequest.newBuilder().setId("0");
    ProtoUtils.update(ImmutableList.of(req), reqBuilder -> {
      reqBuilder.setAt(AuctionType.FIRST_PRICE.getNumber());
      return true;
    });
    assertThat(req.getAt()).isSameInstanceAs(AuctionType.FIRST_PRICE.getNumber());
  }

  @Test
  public void testBuilderConversions() {
    BidRequest.Builder reqBuilder = BidRequest.newBuilder().setId("0");
    BidRequest req = reqBuilder.build();
    assertThat(ProtoUtils.<BidRequest.Builder, BidRequest>built(reqBuilder)).isEqualTo(req);
    assertThat(ProtoUtils.<BidRequest, BidRequest>built(req)).isSameInstanceAs(req);
    assertThat(ProtoUtils.<BidRequest.Builder, BidRequest>built(null)).isNull();
    assertThat(ProtoUtils.builder(req).build()).isEqualTo(reqBuilder.build());
    assertThat(ProtoUtils.<BidRequest.Builder, BidRequest.Builder>builder(reqBuilder))
        .isSameInstanceAs(reqBuilder);
    assertThat(ProtoUtils.<BidRequest, BidRequest.Builder>builder(null)).isNull();
  }
}
