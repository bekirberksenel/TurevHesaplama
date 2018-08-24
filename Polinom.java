import java.util.*;
public class Polinom {
	public static void main(String[] args) {
		String islem = "3x^2+4x-5"; //kullanicidan aldigimiz polinom
		String x = ""; //islemdeki x'i hesaplamak icin kullanicidan aldigimiz deger
		boolean devam = true;
		Scanner k = new Scanner(System.in);
		while(devam){
			boolean dogru = true;
			boolean devamEt = true;
			System.out.println("... 1. Adim : Polinom ... \nTurevini hesaplamak istediginiz polinomu giriniz ");
			islem = k.nextLine();
			System.out.println("... 2. Adim : x Degeri ... \nHesaplamak istediginiz x degerini giriniz ");
			x = k.nextLine();
			x = myReplaceAll(x," ",""); //sayidaki bosluklari temizledik
			x = myReplaceAll(x,"	",""); //sayida tab karakteri kadar bosluk varsa onlari da temizledik
			islem = myReplaceAll(islem," ",""); //islemdeki bosluklari temizledik
			islem = myReplaceAll(islem,"	",""); //islemde tab karakteri kadar bosluk varsa onlari da temizledik
			islem = islem.toLowerCase(); //x in buyuk harf ile girilme durumunu goz onunde bulundurarak kucuk harfe cevirdik
			if(!islem.contains("+") && !islem.contains("-") && !islem.contains("^") && !islem.contains("x")){	
				System.out.println("Hatali bir polinom girdiniz."); //girilen islem bu dort islemden farkli ise hatali
				dogru = false;
			}
			else{
				if(islem.indexOf("^")!=-1){ //polinomun ussu 3'ten buyuk ise hatali
					for(int i = 0 ; i<islem.length(); i++){ 
						if((islem.indexOf("^")+1)<islem.length() && islem.charAt(islem.indexOf("^")+1)>51){
							System.out.println("Hatali bir polinom girdiniz. Us degeri en fazla 3 olabilir.");
							dogru = false;
							devamEt = false;
							break;
						}	
					}
				}
				for(int i = 0 ; i<islem.length(); i++){ //girilen islemde sayilar yoksa veya x veya ussu veya toplama veya cikarma islemi yoksa hatali
					if(devamEt==true&&islem.charAt(i) >= 48 && islem.charAt(i) <= 57 || (islem.charAt(i) == 43 || islem.charAt(i) == 45 || islem.charAt(i)==94 || islem.charAt(i)==120)){ //hatali olmayan durum 	
						devam = true;
					} 
					else { //hatali durum
						if(devamEt==false){
							break;
						}
						System.out.println("Hatali bir polinom girdiniz.");
						dogru = false;
						break;
					}
				}

			}
			if(!x.contains("0")&&!x.contains("1")&&!x.contains("2")&&!x.contains("3")&&!x.contains("4")&&!x.contains("5")&&!x.contains("6")&&!x.contains("7")&&!x.contains("8")&&!x.contains("9")){ //polinomu hesaplamak icin girilen x degeri sayi degilse hatali
				System.out.println("x' i hesaplamak icin hatali bir deger girdiniz.");
				dogru = false;
			}

			if(dogru == true){ //gecerli durum 
				int countArti=0;
				int countEksi=0;
				if(islem.equals("x")){
					islem = "1";
					System.out.println("Islemin 1.turevi = " + islem);
					System.out.println("Islemin sonucu = "+ islem);
					break;
				}
				if(islem.equals("-x")){
					islem = "-1";
					System.out.println("Islemin 1.turevi = " + islem);
					System.out.println("Islemin sonucu = "+ islem);
					break;
				}							
				for(int i = 0; i<islem.length();i++){ //countArti degiskeni ile polinomda gecen arti sayisini tutuyoruz
					if(islem.charAt(i)=='+'){     //turev alirken + olmasi durumunda bu degiskenden yararlanacagiz
						countArti++; 
					}
					if(islem.charAt(i)=='-'){     //countEksi degiskeni ile polinomda gecen eksi sayisini tutuyoruz
						countEksi++;	      //turev alirken - olmasi durumunda bu degiskenden yararlanacagiz 
					}
				}
				String sbt = ""; //polinomun icerisindeki sabit sayiyi bulup sifirlamak icin tuttugumuz degisken
				for(int j = 0; j<islem.length();j++){
					if(islem.indexOf("+")!=-1&&islem.lastIndexOf("+")!=-1&&countArti>=2){ //polinomda ikiden fazla toplama 														      //islemi varsa
						sbt = islem.substring(islem.indexOf("+")+1,islem.lastIndexOf("+")+1); //ve bu islemler 															//arasinda x yoksa
						if(!sbt.contains("x")){					//sabit kismi polinomdan siliyoruz
							islem = islem.substring(0,islem.indexOf("+")) + islem.substring(islem.lastIndexOf("+"));
						}

					} 
				}
				//turev alirken polinomdan silecegimiz sabit sayinin ortada olmasi durumlari
				for(int j = 0; j<islem.length();j++){
					if(islem.indexOf("+")<islem.indexOf("-")&&countArti>=1&&countEksi>=1){ //once + sonra - varsa ve 															 //aradaki kisim
						sbt = islem.substring(islem.indexOf("+")+1,islem.lastIndexOf("-")+1);//x icermiyorsa 														   //sabit kismi polinomdan siliyoruz
						if(!sbt.contains("x")){ 
							islem = islem.substring(0,islem.indexOf("+")) + islem.substring(islem.lastIndexOf("-"));
						}

					}
				}

				for(int j = 0; j<islem.length();j++){
					if(islem.indexOf("-")<islem.indexOf("+")&&countArti>=1&&countEksi>=1){//once - sonra + varsa ve 														   //aradaki kisim
						sbt = islem.substring(islem.indexOf("-")+1,islem.lastIndexOf("+")+1);//x icermiyorsa 														   //sabit kismi polinomdan siliyoruz
						if(!sbt.contains("x")){
							islem = islem.substring(0,islem.indexOf("-")) + islem.substring(islem.lastIndexOf("+"));
						}

					}
				}

				for(int j = 0; j<islem.length();j++){

					if(islem.indexOf("-")!=-1&&islem.lastIndexOf("-")!=-1&&countEksi>=2){//polinomda ikiden fazla cikarma 															   //islemi varsa ve
						sbt = islem.substring(islem.indexOf("-")+1,islem.lastIndexOf("-"));//bu islemler arasinda x 															   //yoksa sabit kismi
						if(!sbt.contains("x")){						   //polinomdan siliyoruz
							islem = islem.substring(0,islem.indexOf("-")) + islem.substring(islem.lastIndexOf("-"));
						}

					}
				}
				sbt = ""; 
				//turev alirken polinomdan silecegimiz sabit sayinin basta olmasi durumlari
				if(islem.contains("+")&&!islem.substring(0,islem.indexOf("+")).contains("x")){ //islem + iceriyorsa ve islemin
					islem = islem.substring(islem.indexOf("+")+1);//0. indeksi ile ilk + nin indeksi arasinda x yoksa 
				}						      //sabit kismi polinomdan siliyoruz
				if(islem.contains("-")&&!islem.substring(0,islem.indexOf("-")).contains("x")){//islem - iceriyorsa ve islemin
					islem = islem.substring(islem.indexOf("-"));  //0. indeksi ile ilk - nin indeksi arasinda x yoksa
				}						      //sabit kismi polinomdan siliyoruz
				//turev alirken polinomdan silecegimiz sabit sayinin sonda olmasi durumlari
				if(islem.contains("+")&&!islem.substring(islem.lastIndexOf("+")+1,islem.length()).contains("x")){//islemde 
					islem = islem.substring(0,islem.lastIndexOf("+"));//bulunan en son + dan sonraki kisimda x yoksa sabit 
				}						          //kismi polinomdan siliyoruz
				if(islem.contains("-")&&!islem.substring(islem.lastIndexOf("-")+1,islem.length()).contains("x")){//islem
					islem = islem.substring(0,islem.lastIndexOf("-"));// - iceriyorsa ve islemin son - bulunan indeksinden 
				}                                                         //islemin sonuna kadar olan kisimda x yoksa sabit  
											  //kismi polinomdan siliyoruz
				for(int i = 0 ; i<islem.length(); i++){ 	          //sabitin turevi 0 oldugu icin sabit kisimlari sildik
					if(islem.charAt(i)=='x' && islem.charAt(i+1)!='^' && (i+1)<islem.length() ){
						islem = islem.substring(0,i) + islem.substring(i+1);
					}//islemde x varsa ve us yoksa turevini almak icin x li kismi atip x in onundeki kismi aliyoruz   						 //ornek verecek olursak 2x in turevi 2 olur
					if(islem.charAt(islem.length()-1)=='x'){//islemin son indeksinde x varsa turevini alacagiz
						islem = islem.substring(0,islem.length()-1);
					 //ornek verecek olursak islemin son kisminda 7x varsa x i atiyoruz ve sonuc 7 oluyor
					}
					if(islem.charAt(i)=='x' && islem.charAt(i+1)=='^' && (i+1)<islem.length()){//x in yaninda ussu varsa ve
						if(islem.charAt(i+2)=='2'&& i==0){		    //x in uzeri 2 ise ve x islemin basinda ise
							islem=islem.substring(0,i) + "2*" + islem.substring(i,i+2)+"1"+islem.substring(i+3);
						}//2 yi carpim olarak x in yanindaki kisma yaziyoruz ussu 1 yapip kalan islemi yanina ekliyoruz
						else if(islem.charAt(i+2)=='2'){ //x uzeri 2 islemin basinda degilse ust kisimda yaptigimiz isleme ek olarak x in solunda da bir sayi olmasi durumunu goz onunde bulundurarak 2 nin sagina ve soluna carpma isareti koyuyoruz, daha sonra eger 2 nin solunda sayi yoksa islem karisikligini onlemek icin * yi temizleyecegiz
							islem=islem.substring(0,i) + "*2*" + islem.substring(i,i+2)+"1"+islem.substring(i+3);
						}

					}
				}
				for(int i = 0 ; i<islem.length(); i++){ 
					if(islem.charAt(i)=='x' && islem.charAt(i+1)!='^' && (i+1)<islem.length() ){
						islem = islem.substring(0,i) + islem.substring(i+1);
					}//ayni sekilde islemde x var ve ussu yoksa x i atip onundeki sayiyi aliyoruz
					if(islem.charAt(islem.length()-1)=='x'){
						islem = islem.substring(0,islem.length()-1);
					}//islemin son indeksinde x varsa turevini aliyoruz
					if(islem.charAt(i)=='x' && islem.charAt(i+1)=='^' && (i+1)<islem.length()){//x in yaninda ussu varsa
						if(islem.charAt(i+2)=='3'&& i==0){//x in uzeri 3 ise ve x islemin basinda ise
							islem=islem.substring(0,i) + "3*" + islem.substring(i,i+2)+"2"+islem.substring(i+3);
						}//3 u carpim olarak x in yanindaki kisma yaziyoruz ussu 2 yapip kalan islemi yanina ekliyoruz
						else if(islem.charAt(i+2)=='3'){//x uzeri 3 islemin basinda degilse ust kisimda yaptigimiz isleme ek olarak x in solunda da bir sayi olmasi durumunu goz onunde bulundurarak 3 un sagina ve soluna carpma isareti koyuyoruz, daha sonra eger 3 un solunda sayi yoksa islem karisikligini onlemek icin * yi temizleyecegiz
							islem=islem.substring(0,i) + "*3*" + islem.substring(i,i+2)+"2"+islem.substring(i+3);
						}

					}
				}

				if(islem.charAt(islem.length()-1) == '-'){ //islemin en sonunda x varsa ve ussu yoksa turevini almak icin x i islemden silmis kalan kismi almistik, eger islemin sonunda -x varsa islemin sonunda - kalmamasi -1 olmasi icin islemin sonunda 1 ekliyoruz
					islem = islem + '1';
				}
				if(islem.charAt(islem.length()-1) == '+'){//ayni sekilde islemin en sonunda x varsa ve ussu yoksa turevini almak icin x i islemden silmis kalan kismi almistik, eger islemin sonun +x varsa islemin sonunda + kalmamasi +1 olmasi icin islemin sonuna 1 ekliyoruz
					islem = islem + '1';
				}
//x uzeri 2 li ve x uzeri 3 lu ifadeler islemin ortasinda ise turev almak icin uslu kismi 1 azaltip x in katsayisi ile carpmak icin carpma yaptigimiz sayinin sagina ve soluna * isareti koymustuk, islem karisikligi olmamasi icin turev aldiktan sonra +* li bir kisim varsa bu kisimlari + ya, *+ li kisimlari + ya , -* li kisimlari - ye, *- li kisimlari - ye, ** li kisimlari * ya, ^* li kisimlari ^ ye, *^ lu kisimlari ^ ye ceviriyoruz, boylece islemin turevini almis oldumuz kisimdaki hatalari temizlemis olduk	
				islem = myReplaceAll(islem,"+*","+");
				islem = myReplaceAll(islem,"*+","+");
				islem = myReplaceAll(islem,"-*","-");
				islem = myReplaceAll(islem,"*-","-");
				islem = myReplaceAll(islem,"**","*");
				islem = myReplaceAll(islem,"^*","^");
				islem = myReplaceAll(islem,"*^","^");
				
				System.out.println("Polinomun 1.turevi = " + islem);
//islem yaparken us islemini kullanmak yerine ornegin x in uzeri 2 ise ^2 olan kismi atip x*x , x in uzeri 1 ise ^1 olan kismi atip x i alarak islemi temiz hale getiriyoruz, bundan sonraki kisimda elimizdeki String turundeki islemli ifadede x i yerine yazarak islemi yapacagiz
				for(int i = 0; i<islem.length(); i++){ //ussuyu carpma ıslemine cevirdik
					if(islem.charAt(i)=='^'){
						if(islem.charAt(i+1)=='2' ){ //ussu 2 ise
							islem = islem.substring(0,i)+"*"+islem.charAt(i-1)+islem.substring(i+2);
						} 
						if(islem.charAt(i+1)=='1'){ //ussu 1 ise
							islem = islem.substring(0,i)+islem.substring(i+2);
						}
					}
				}

				//System.out.println(islem);

				//islem = myReplaceAll(islem,"*-","*");
				if(islem.indexOf("+")!=-1&&islem.length()>2&&islem.charAt(0)=='-'&&!islem.substring(1,islem.indexOf("+")).contains("x")&&!islem.substring(1,islem.indexOf("+")).contains("*")&&!islem.substring(1,islem.indexOf("+")).contains("-")){
					islem = islem.substring(islem.indexOf("+")+1)+islem.substring(0,islem.indexOf("+"));
				}//islemin ilk kisminda x li bir ifade yoksa yani sabit bir sayi varsa ve basinda - varsa ve islemin diger kisminda + li bir ifade varsa ilk kisimla ikinci kismi yer degistiriyoruz, bunu yapmamizin amaci bastaki ifadeyi pozitif yaparak cikarma islemi yapmak. Ornek verecek olursak -5+27 ifadesinin sonucuyla 27-5 ifadesinin sonucu ayni olur.
				islem = myReplaceAll(islem,"*-","/");//*- sayisini bulacagiz, bunun icin *- olan bolumu / ye cevirecegiz ve islemde / nun kac kere gectigini bulacagiz. eger / sayisi cift ise islemin sonucu + olur, bu yuzden *- olan kismi daha sonra * ya cevirecegiz 
				int carpiEksi = 0; 
				if(islem.contains("/")){ 
					for(int i =0;i<islem.length();i++){
						if(islem.charAt(i)=='/')
							carpiEksi++; //islemde gecen *- sayisi
					}
				}
				islem = myReplaceAll(islem,"/","*-"); //islemde gecen *- sayisini bulduktan sonra *- yi tekrar islemde ayni yerlere koyuyoruz
				if(islem.contains("*-")){ //bu duruma gelindiginde islem *- iceriyorsa
					for(int i =0;i<islem.length();i++){
						if(carpiEksi%2==0){ // *- sayisi cift ise 2 ile bolumunden kalan 0 olur, dolayisiyla islem yapilan kisim + olur, bunun icin *- li kisimlari * ya ceviriyoruz
							islem = myReplaceAll(islem,"*-","*");
						}
						else{ // *- sayisi tek ise islemin sonucu negatif olur, bunun icin *- li kisimlari * ya ceviriyoruz ve islemin sonucu - oldugu icin islemin basina - koyuyoruz
							islem = "-" + myReplaceAll(islem,"*-","*");
						}
					}
				}
				islem = myReplaceAll(islem,"x",x); //islemde x gordugumuz yerleri kullanicidan x in hesaplanmasi icin aldigimiz x degeriyle degistiriyoruz
				//System.out.println(islem);
				String xN = x+"";
				//islem = "-14+2";

				islem = belliBirIslemiIsle(islem, "*"); //islem onceligi icin oncelikle carpma islemlerini yapiyoruz
				//System.out.println(islem);
				if(islem.indexOf("+")!=-1&&islem.length()>2&&islem.charAt(0)=='-'&&!islem.substring(1,islem.indexOf("+")).contains(xN)&&!islem.substring(1,islem.indexOf("+")).contains("*")&&!islem.substring(1,islem.indexOf("+")).contains("-")){//carpma isleminden sonra islemin basinda - varsa ve islemin daha sonraki kisminda toplama islemi bulunan bir kisim varsa toplama islemi olan kisimla bastaki kismi yer degistiriyoruz    
					islem = islem.substring(islem.indexOf("+")+1)+islem.substring(0,islem.indexOf("+"));
				}
				if(islem.contains("---")){ //islemin herhangi bir yerinde --- kaldiysa bu kismi - ye ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "-" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("+++")){ //islemin herhangi bir yerinde +++ kaldiysa bu kismi + ya ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "+" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("+--")){ //islemin herhangi bir yerinde +-- kaldiysa bu kismi + ya ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "+" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("-+-")){ //islemin herhangi bir yerinde -+- kaldiysa bu kismi + ya ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "+" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("--+")){ //islemin herhangi bir yerinde --+ kaldiysa bu kismi + ya ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "+" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("++-")){ //islemin herhangi bir yerinde ++- kaldiysa bu kismi - ye ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "-" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("-++")){ //islemin herhangi bir yerinde -++ kaldiysa bu kismi - ye ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "-" + islem.substring(i+3);
						}
					}
				}
				if(islem.contains("+-+")){ //islemin herhangi bir yerinde +-+ kaldiysa bu kismi - ye ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+2&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'&&islem.charAt(i+2)=='-'){
							islem = islem.substring(0,i) + "-" + islem.substring(i+3);
						}
					}
				}
				islem = belliBirIslemiIsle(islem, "+"); //toplama islemlerini yapiyoruz
				//System.out.println(islem);
				islem = belliBirIslemiIsle(islem, "-"); //cikarma islemlerini yapiyoruz

				if(islem.length()>2&&islem.substring(0,2).equals("--")){//islemin basinda -- kaldiysa -- = + oldugu icin -- olan kismi atip sonraki kismi aliyoruz
					islem = islem.substring(2);
				}
				if(islem.length()>2&&islem.substring(0,2).equals("++")){//islemin basinda ++ kaldiysa ++ = + oldugu icin ++ olan kismi atip sonraki kismi aliyoruz
					islem = islem.substring(2);
				}	
				if(islem.length()>2&&islem.substring(0,2).equals("-+")){//islemin basinda -+ kaldiysa -+ = - oldugu icin -+ olan kismi atip bas kisma - ekleyip sonraki kismi aliyoruz
					islem = "-" + islem.substring(2);
				}
				if(islem.length()>2&&islem.substring(0,2).equals("+-")){//islemin basinda +- kaldiysa +- = - oldugu icin +- olan kismi atip bas kisma + ekleyip sonraki kismi aliyoruz
					islem = "-" + islem.substring(2);
				}	
				//System.out.println(islem);
				islem = belliBirIslemiIsle(islem, "*"); //kalan durum icin carpma islemi varsa yapiyoruz
				if(islem.contains("--")){ //islemin herhangi bir yerinde -- kaldiysa bu kismi + ya ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+1&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'){
							islem = islem.substring(0,i) + "+" + islem.substring(i+2);
						}
					}
				}
				if(islem.contains("-+")){ //islemin herhangi bir yerinde -+ kaldiysa bu kismi - ye ceviriyoruz
					for(int i =0;i<islem.length();i++){
						if(islem.length()>i+1&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'){
							islem = islem.substring(0,i) + "-" + islem.substring(i+2);
						}
					}
				}
				if(islem.contains("+-")){ //islemin herhangi bir yerinde +- kaldiysa bu kismi - ye ceviriyoruz
					for(int i =0;i<islem.length();i++){ 
						if(islem.length()>i+1&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'){
							islem = islem.substring(0,i) + "-" + islem.substring(i+2);
						}
					}
				}
				if(islem.contains("++")){ //islemin herhangi bir yerinde ++ kaldiysa bu kismi + ya ceviriyoruz
					for(int i =0;i<islem.length();i++){
						if(islem.length()>i+1&&(i-1)>=0&&islem.charAt(i)=='-'&&islem.charAt(i+1)=='-'){
							islem = islem.substring(0,i) + "+" + islem.substring(i+2);
						}
					}
				}
				islem = belliBirIslemiIsle(islem, "+"); //kalan durum icin toplama islemlerini yapiyoruz
				//System.out.println(islem);
				islem = belliBirIslemiIsle(islem, "-"); //kalan durum icin cikarma islemlerini yapiyoruz

				System.out.println("Islemin sonucu = "+ islem);						

				break;
			}
		}

	}

	private static String myReplaceAll(String formul, String degistirilen, String yeni) { //replace metodu
		int i = 0;
		String strN = "" ;  
		while(i < formul.length()){
			if(formul.indexOf(degistirilen) != -1){				
				i = formul.indexOf(degistirilen);
				formul = formul.substring(0, i) + yeni + formul.substring(i+degistirilen.length());			
			}
			else return formul;
		}
		return strN ;
	}

	private static int stringToInt(String input) {	//Sayiyi String turunde aldik, basamaklarini dongu icerisinde tek tek cektik ve cektigimiz basamagi bulundugu basamaga gore 10 uzeri sekilde carptik ve her bir islem sonucunda buldugumuz degeri toplayarak stringi int e cevirmis olduk.
		String tersSayiStr = "";
		int j = 0, donulecekSayi = 0 ;
		int eksiCarpani = 1;
		if(input.startsWith("-")) {
			eksiCarpani = -1;
			input = input.substring(1);
		}
		tersSayiStr = tersCevir(input);
		for(j = 0; j < tersSayiStr.length() ; j++){
			donulecekSayi += charToDigit(tersSayiStr.charAt(j)) * (int)Math.pow(10, j);
		}
		return donulecekSayi * eksiCarpani;
	}

	private static String tersCevir(String sayiStr) {
		int i = 0;
		String yeniSayi = "";
		for(i = sayiStr.length()-1 ; i > -1 ; i--){ //sayiyi ters cevirdik
			yeniSayi = yeniSayi + sayiStr.charAt(i);
		}
		return yeniSayi;
	}

	private static int charToDigit(char sayiStr) { //harf alir, sayi verir
		switch (sayiStr) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		}
		return -1;
	}

	private static String belliBirIslemiIsle(String formul, String islemStr) { //polinomu ve islemi parametre olarak alir, islem formulde bulundugu surece parametre olarak islemin solundaki sayiyi, islemi ve islemin sagindaki sayiyi parametre olarak alan islemiSonuclandir metodunda sonucu hesaplar, sonuc hesaplanirken islemi sonuclandir metodunda parametre olarak gonderilen islemin solundaki ve sagindaki sayi bulunurken yanindakiSayiyiBul metodu cagrilir burada boolean deger true ise sayi islemin sagindadir. 
		int sonuc = 0;
		while (formul.indexOf(islemStr) != -1) {
			sonuc = islemiSonuclandir(yanindakiSayiyiBul(formul, formul.indexOf(islemStr), false), islemStr,
					yanindakiSayiyiBul(formul, formul.indexOf(islemStr), true)); //parametre olarak operatorun solundaki sayi, operator ve operatorun sagindaki sayi gonderilen islemiSonuclandir metodunda gonderilen operatorun islemi yapilir, sonuc int olarak hesaplanir ve sonuca atanir
			formul = myReplaceAll(formul, yanindakiSayiyiBul(formul, formul.indexOf(islemStr), false)  
					+ islemStr + yanindakiSayiyiBul(formul, formul.indexOf(islemStr), true), "" + sonuc);
//sonuc kismi polinom uzerinde islem yapilan kisma yazilir ornegin 3+5-2 isleminde parametre olarak - operatoru geldiyse 5-2 islemi yapilarak sonuca atanmis, sonuc = 3 olmustu. Bu durumda elimizdeki islem 3+3 olur ve formul Stringini bu deger olarak degistirir.
			formul = myReplaceAll(formul, "+-", "-"); //formulde +- kaldiysa - ye cevirir
			if (islemBittiMi(formul)) {
				return formul;
			}
		}
		return formul; 
	}

	private static int yanindakiSayiyiBul(String formul, int indeks, boolean saginda){ //operatorun indeksi gonderilen formulde boolean deger true ise sayi islemin sagindadir, bu durumda islemin sagindaki sayiyi alarak int e cevirerek dondurur. operatorun indeksi gonderilen formulde boolean deger false ise sayi islemin solundadir, bu durumda islemin solundaki sayiyi alarak int e cevirerek dondurur.
		int i = 0;
		if(saginda) {
			String sag = formul.substring(indeks+1);
			for(i = 1; i< sag.length(); i++) {      
				if(islemMi(sag.charAt(i))) {
					return stringToInt(sag.substring(0, i));
				}
			}
			return stringToInt(sag);
		}
		else {
			String sol = formul.substring(0,indeks);
			for(i = sol.length()-1; i>-1; i--) {
				if(islemMi(sol.charAt(i))) {
					return stringToInt(sol.substring(i+1));
				}
			}
			return stringToInt(sol);
		}
	}
	private static int islemiSonuclandir(int d, String islem, int e) { //matematiksel islem operatorlerini tanimlayarak yapilacak islemi belirledik.

		if(islem.equals("*")) {
			return d * e;
		}
		else if(islem.equals("+")) {
			return d + e;
		}
		else if(islem.equals("-")) {
			return d - e;
		}
		else {
			System.out.println("Beklenenden farkli bir islem geldi!"); 
			return 0;		
		}
	}
	private static boolean islemMi(char c) { //operator kontrolu 
		if(c == '*' || c == '+' || c == '-') {
			return true;
		}
		else return false;
	}
	private static boolean islemBittiMi(String formul) {
		if(formul.startsWith("-")) {
			return true;
		}
		return false;
	}

}