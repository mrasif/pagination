package in.mrasif.app.pagination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.mrasif.app.lib.pagination.PageClickListener;
import in.mrasif.app.lib.pagination.Paginator;
import in.mrasif.app.pagination.R;

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
    public void onPageClick(int page) {
        Toast.makeText(this, "Page: "+page, Toast.LENGTH_SHORT).show();
        pnPager.setPages(pages,page);
    }
}
