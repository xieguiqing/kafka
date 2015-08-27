/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.streaming.kstream.internals;

import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streaming.kstream.KStream;
import org.apache.kafka.streaming.kstream.KStreamBuilder;
import org.apache.kafka.streaming.kstream.ValueMapper;
import org.apache.kafka.test.KStreamTestDriver;
import org.apache.kafka.test.MockProcessorDef;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class KStreamFlatMapValuesTest {

    private String topicName = "topic";

    private IntegerDeserializer keyDeserializer = new IntegerDeserializer();
    private StringDeserializer valDeserializer = new StringDeserializer();

    @Test
    public void testFlatMapValues() {
        KStreamBuilder builder = new KStreamBuilder();

        ValueMapper<String, Iterable<String>> mapper =
            new ValueMapper<String, Iterable<String>>() {
                @Override
                public Iterable<String> apply(String value) {
                    ArrayList<String> result = new ArrayList<String>();
                    result.add(value.toLowerCase());
                    result.add(value);
                    return result;
                }
            };

        final int[] expectedKeys = new int[]{0, 1, 2, 3};

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    KStreamTopology initializer = new MockKStreamTopology();
=======
    KStreamInitializer initializer = new KStreamInitializerImpl(null, null, null, null);
>>>>>>> new api model
=======
    KStreamInitializer initializer = new KStreamInitializerImpl();
>>>>>>> wip
=======
    KStreamTopology initializer = new MockKStreamTopology();
>>>>>>> wip
    KStreamSource<Integer, String> stream;
    MockProcessor<Integer, String> processor;
=======
        KStreamTopology initializer = new MockKStreamTopology();
        KStreamSource<Integer, String> stream;
=======
        KStream<Integer, String> stream;
<<<<<<< HEAD
>>>>>>> wip
        MockProcessor<Integer, String> processor;
>>>>>>> compile and test passed
=======
        MockProcessorDef<Integer, String> processor;
>>>>>>> kstream test fix

        processor = new MockProcessorDef<>();
        stream = builder.from(keyDeserializer, valDeserializer, topicName);
        stream.flatMapValues(mapper).process(processor);

        KStreamTestDriver driver = new KStreamTestDriver(builder);
        for (int i = 0; i < expectedKeys.length; i++) {
            driver.process(topicName, expectedKeys[i], "V" + expectedKeys[i]);
        }

        assertEquals(8, processor.processed.size());

        String[] expected = new String[]{"0:v0", "0:V0", "1:v1", "1:V1", "2:v2", "2:V2", "3:v3", "3:V3"};

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], processor.processed.get(i));
        }
    }
}
