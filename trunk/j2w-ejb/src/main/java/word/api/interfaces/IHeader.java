package word.api.interfaces;

public interface IHeader extends IHasElement{

	public void setHideHeaderAndFooterFirstPage(boolean value);
	public boolean getHideHeaderAndFooterFirstPage();
	
	public String getHideHeaderAndFooterFirstPageXml();
	
}
