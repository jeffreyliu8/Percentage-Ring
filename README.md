[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

# Percentage-Ring

This is a custom percentage ring.

Using Percentage Ring
----------------

### Setup
In your app module build.gradle(before jcenter is ready)
```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/jeffreyliu8/PercentageRing"
    }
}
```


##### Dependencies
```groovy
dependencies {
    compile 'jliu:percentagering:0.0.1'
}
```

A quick example is shown below:

```java
PercentRingView ring = (PercentRingView) findViewById(R.id.ring);
ring.setPercentageAndAnimate(2, 4, "of 4 days");
```

And for layout xml:
```xml
 <jliu.percentagering.PercentRingView
                    xmlns:customNS="http://schemas.android.com/apk/res-auto"
                    android:id="@+id/ring"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    customNS:denominator="4"
                    customNS:lowerText="of 4 days"
                    customNS:numerator="2"
                    customNS:ringColor="@color/colorRingFillGreen" />
```
![Output sample](https://github.com/jeffreyliu8/Percentage-Ring/blob/master/preview.gif)

Requirements
--------------
Requires a minimum SDK version of 11

Developed By
-------
Jeffrey Liu - <jeffreyliu8@gmail.com>

License
-------

    Copyright 2017 Jeffrey Liu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
