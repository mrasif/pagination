package in.mrasif.app.lib.pagination;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Paginator extends LinearLayout {

    View rootView;
    ImageView btnPrev, btnNext;
    HorizontalScrollView hsv;
    LinearLayout llPages;
    int buttonColor, buttonColorActive, textColor, textColorActive;
    Drawable iconNext, iconPrev;
    Integer buttonWidth, buttonHeight;
    PageClickListener listener;
    List<Integer> pages;
    Integer current;


    public Paginator(Context context) {
        super(context);
        initUI(context, null);
    }

    public Paginator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context, attrs);
    }

    public Paginator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Paginator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI(context, attrs);
    }

    @SuppressLint("NewApi")
    private void initUI(Context context, AttributeSet attrs) {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.bar_pageinator, this, false);
        rootView.setMinimumWidth(getMinimumWidth());
        rootView.setMinimumHeight(getMinimumHeight());

        btnPrev = rootView.findViewById(R.id.btnPrev);
        btnNext = rootView.findViewById(R.id.btnNext);
        llPages = rootView.findViewById(R.id.llPages);
        hsv = rootView.findViewById(R.id.hsv);

        setAttributes(context, attrs);
        pages = new ArrayList<>();
        current = 0;

        addView(rootView);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Paginator, 0, 0);
        try {
            buttonColor = ta.getColor(R.styleable.Paginator_buttonColor, Color.WHITE);
            buttonColorActive = ta.getColor(R.styleable.Paginator_buttonColorActive, Color.WHITE);
            textColor = ta.getColor(R.styleable.Paginator_textColor, Color.BLACK);
            textColorActive = ta.getColor(R.styleable.Paginator_textColorActive, Color.BLACK);
            iconNext = ta.getDrawable(R.styleable.Paginator_iconNext);
            iconPrev = ta.getDrawable(R.styleable.Paginator_iconPrev);
            buttonWidth=ta.getDimensionPixelSize(R.styleable.Paginator_buttonWidth,0);
            buttonHeight=ta.getDimensionPixelSize(R.styleable.Paginator_buttonHeight,0);
            btnPrev.setBackgroundColor(buttonColor);
            btnNext.setBackgroundColor(buttonColor);
            btnNext.setImageDrawable(iconNext);
            btnPrev.setImageDrawable(iconPrev);

        } finally {
            ta.recycle();
        }
    }

    public void setOnPageClickListener(PageClickListener listener) {
        this.listener = listener;
    }

    public void setPages(List<Integer> pages, Integer current) {
        this.pages = pages;
        this.current = current;
        renderPages();
    }

    private void renderPages() {

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int next=current+1;
                if (next<=pages.size() && null!=listener){
                    listener.onPageClick(next);
                }
            }
        });

        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int prev=current-1;
                if (prev>0 && null!=listener){
                    listener.onPageClick(prev);
                }
            }
        });

        llPages.removeAllViews();
        for (final Integer page : pages) {
            Button button = getButton();
            if (page==current) {
                button.setBackgroundColor(buttonColorActive);
                button.setTextColor(textColorActive);
                button.requestFocus();
            }
            else {
                button.setBackgroundColor(buttonColor);
                button.setTextColor(textColor);
            }

            button.setWidth(buttonWidth);
            button.setHeight(buttonHeight);
            button.setText(String.valueOf(page));
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener && current != page) {
                        listener.onPageClick(page);
                    }
                }
            });
            llPages.addView(button);
        }


//        Button btnChild=(Button) llPages.getChildAt(current-1);
//        System.out.println(hsv.getScrollX());
//        hsv.scrollTo(btnChild.getWidth()*current,btnChild.getTop());


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int scrollWidth=buttonWidth+hsv.getLeft();
//        if (scrollWidth==0){
//            scrollWidth=1;
//        }
//        System.out.println("Scroll: "+hsv.getMeasuredWidth()+"\t Button: "+scrollWidth);
//        hsv.scrollTo(scrollWidth*current,0);
    }

    private Button getButton() {
        Button button = (Button) LayoutInflater.from(getContext()).inflate(R.layout.button_page, null, false);
        return button;
    }
}
