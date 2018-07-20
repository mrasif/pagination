# Pagination
A simple Android Pagination Library. It is very easy to use, to use this library follow thease steps.

## For Gradle:
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency:
```
dependencies {
        compile 'com.github.mrasif:pagination:v1.0.2'
}
```
### For Maven:
Step 1. Add the JitPack repository to your build file:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
Step 2. Add the dependency:
```
<dependency>
    <groupId>com.github.mrasif</groupId>
    <artifactId>pagination</artifactId>
    <version>v1.0.2</version>
</dependency>
```
### For SBT:
Step 1. Add the JitPack repository to your build.sbt file:
```
resolvers += "jitpack" at "https://jitpack.io"
```
Step 2. Add the dependency:
```
libraryDependencies += "com.github.mrasif" % "pagination" % "v1.0.2"
```
### For Leiningen:
Step 1. Add it in your project.clj at the end of repositories:
```
:repositories [["jitpack" "https://jitpack.io"]]
```
Step 2. Add the dependency:
```
:dependencies [[com.github.mrasif/pagination "v1.0.2"]]
```

### Add this in your layout xml file:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">

    <in.mrasif.app.lib.pagination.Paginator
        android:id="@+id/pnPager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#801e1e"
        app:buttonWidth="45dp"
        app:buttonHeight="45dp"
        app:buttonColor="#801e1e"
        app:buttonColorActive="#a45959"
        app:textColor="#000000"
        app:textColorActive="#000000"
        app:iconNext="@drawable/next"
        app:iconPrev="@drawable/prev"/>

</LinearLayout>
```

### Add this in your activity java files:
```
public class MainActivity extends AppCompatActivity implements PageClickListener {

    Paginator pnPager;
    List<Integer> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pnPager=findViewById(R.id.pnPager);
        pages=new ArrayList<>();

        for (int i=1; i<=20; i++){
            pages.add(i);
        }

        pnPager.setOnPageClickListener(this);
        pnPager.setPages(pages,1);

    }

    @Override
    public void onPageClick(View view, int page) {
        Toast.makeText(this, "Page: "+page, Toast.LENGTH_SHORT).show();
        pnPager.setPages(pages,page);
    }
}
```
Note: onPageClick method will trigger when user click on previous, next or page number button.

You are done.
