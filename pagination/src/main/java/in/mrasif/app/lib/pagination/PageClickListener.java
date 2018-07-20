package in.mrasif.app.lib.pagination;
import android.view.View;
public interface PageClickListener {
    /**
    * onPageClick the provided PageClickEvent.
    *
    * @param view View that will return view.
    * @param page int that will return target page number.
    */
    void onPageClick(View view, int page);
}
