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
Requires a minimum SDK version of 14
