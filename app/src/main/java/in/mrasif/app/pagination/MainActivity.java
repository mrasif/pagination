package in.mrasif.app.pagination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.mrasif.app.lib.pagination.PageClickListener;
import in.mrasif.app.lib.pagination.Paginator;

import in.mrasif.app.pagination.R;

public class MainActivity extends AppCompatActivity implements PageClickListener {

    Paginator paginator1, paginator2;
    List<Integer> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paginator1=findViewById(R.id.paginator1);
        paginator2=findViewById(R.id.paginator2);
        pages=new ArrayList<>();

        for (int i=1; i<=20; i++){
            pages.add(i);
        }

        paginator1.setOnPageClickListener(this);
        paginator2.setOnPageClickListener(this);
        paginator1.setPages(pages,1);
        paginator2.setPages(pages,1);

    }

    @Override
    public void onPageClick(View view, int page) {
        switch(view.getId()){
            case R.id.paginator1: {
                Toast.makeText(this, "Page-1: "+page, Toast.LENGTH_SHORT).show();
                paginator1.setPages(pages,page);
            } break;
            case R.id.paginator2: {
                Toast.makeText(this, "Page-2: "+page, Toast.LENGTH_SHORT).show();
                paginator2.setPages(pages,page);
            } break;
        }

    }
}
