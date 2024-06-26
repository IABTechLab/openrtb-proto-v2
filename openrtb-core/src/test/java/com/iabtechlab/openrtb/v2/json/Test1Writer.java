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

package com.iabtechlab.openrtb.v2.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.iabtechlab.openrtb.v2.Test.Test1;
import java.io.IOException;

/**
 * Regular extension: {@code "test1": "data1"}, message type {@code Test1}.
 */
class Test1Writer extends OpenRtbJsonExtWriter<Test1> {

  @Override protected void write(Test1 ext, JsonGenerator gen) throws IOException {
    gen.writeStringField("test1", ext.getTest1());
  }
}
