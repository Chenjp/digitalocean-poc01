package org.k4security.api.victim0917;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v2/customer/contacts")
public class CustomerContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// This is the key: use getPathInfo() to get the rest of the path
	String pathInfo = req.getPathInfo();

	resp.setContentType("text/plain");
	PrintWriter out = resp.getWriter();
	out.println("PathInfo:" + pathInfo);
	String text = "id,first_name,last_name,email,phone,creatorEmail\n"
		+ "1,Markus,McGrowther,mmcgrowther0@geocities.com,992-809-7877,mmcgrowther0@skyrock.com\n"
		+ "2,Ranna,Le Prevost,rleprevost1@businessweek.com,493-858-3758,rleprevost1@de.vu\n"
		+ "3,Benn,McNae,bmcnae2@sakura.ne.jp,652-850-5218,bmcnae2@amazon.com\n"
		+ "4,Bil,Blaiklock,bblaiklock3@umich.edu,915-404-8720,bblaiklock3@disqus.com\n"
		+ "5,Nyssa,Esom,nesom4@prlog.org,173-218-7020,nesom4@mail.ru\n"
		+ "6,Audra,Drinkwater,adrinkwater5@nytimes.com,175-173-8741,adrinkwater5@webeden.co.uk\n"
		+ "7,Caron,Robers,crobers6@blinklist.com,834-939-9790,crobers6@wiley.com\n"
		+ "8,Trumann,Barbery,tbarbery7@ning.com,267-914-4985,tbarbery7@etsy.com\n"
		+ "9,Benjy,Schleicher,bschleicher8@odnoklassniki.ru,429-946-4683,bschleicher8@fc2.com\n"
		+ "10,Shell,Carsberg,scarsberg9@cbslocal.com,119-744-2363,scarsberg9@about.com\n"
		+ "11,Cullan,Grisewood,cgrisewooda@a8.net,815-159-6743,cgrisewooda@e-recht24.de\n"
		+ "12,Glenn,Mc Harg,gmchargb@google.pl,659-184-4405,gmchargb@infoseek.co.jp\n"
		+ "13,Ingrim,Aujouanet,iaujouanetc@artisteer.com,764-696-2714,iaujouanetc@creativecommons.org\n"
		+ "14,Joby,Cosford,jcosfordd@senate.gov,613-456-5070,jcosfordd@instagram.com\n"
		+ "15,Nikola,Schwand,nschwande@webnode.com,674-697-1927,nschwande@printfriendly.com\n"
		+ "16,Sibelle,Dalgetty,sdalgettyf@istockphoto.com,835-148-8197,sdalgettyf@biglobe.ne.jp\n"
		+ "17,Brucie,Telling,btellingg@indiegogo.com,972-646-9787,btellingg@patch.com\n"
		+ "18,Antin,Ivashinnikov,aivashinnikovh@mysql.com,939-182-4835,aivashinnikovh@latimes.com\n"
		+ "19,Charlene,Frisdick,cfrisdicki@furl.net,605-742-1518,cfrisdicki@indiegogo.com\n"
		+ "20,Huberto,Jentin,hjentinj@ebay.com,530-662-9261,hjentinj@mashable.com\n";
	out.print(text);
    }
}
