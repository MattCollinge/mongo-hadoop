/*
 * Copyright 2011 10gen Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mongodb.hadoop.examples.enron;

// Mongo

import org.bson.*;
import com.mongodb.hadoop.util.*;

// Commons
import org.apache.commons.logging.*;

// Hadoop
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

// Java
import java.io.*;
import java.util.*;

public class EnronMailReducer
	extends Reducer <BSONObject, IntWritable, BSONObject, IntWritable> {

    private static final Log LOG = LogFactory.getLog( EnronMailReducer.class );

    @Override
    public void reduce( final BSONObject pKey,
                        final Iterable<IntWritable> pValues,
                        final Context pContext )
            throws IOException, InterruptedException{
        int sum = 0;
        LOG.info( "Reducing Key: " + pKey);
        for ( final IntWritable value : pValues ){
            sum += value.get();
        }
        pContext.write( pKey, new IntWritable(sum) );
    }


}

