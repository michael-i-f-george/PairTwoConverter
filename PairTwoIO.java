package org.chess.pairtwo;

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
 
// TODO: to improve code below (it's quick and dirty (no error handling, no optimization, assertion).
 
public class PairTwoIO {

/********************
* INNER CLASSES		*
*********************/
	
	
	
	private class Round extends ArrayList<PairTwoGame> {
//         private Calendar mdtDate ;
//        
//         public Calendar getDate() {
//                 return mdtDate;
//         }
//         public void setDate(Calendar dtDate) {
//                 mdtDate=dtDate;
//         }
//        private String mdDate ;
//        
//        public String getDate() {
//                return msDate;
//        }
//        public void setDate(String dtDate) {
//                msDate=dtDate;
//        }
//        
//    	public Date getDate() {
//    		return mdDate;
//    	}
//    	public void setDate(String sDate){
//    		String s = "2011-07-08 03:48:45";
//    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mi:ss");
//    		Date d = sdf.parse(s);	
//
//    		DateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd");
//    		try {
//    			this.mdDate = dfTemp.parse(sDate);
//    		}
//    		catch (ParseException e) {
//    			e.printStackTrace();
//    		}		
//    	}
        private Date mcalDate;
        public void setDate(Date calDate) {
        	mcalDate = calDate;
        }
        public Date getDate(){
        	return mcalDate;
        }
        public Round(Date calDate) {
        	mcalDate = calDate;
        }
 }
	
	
	private class PairTwoGame {
		 
		// Member variables.
		        private String msBoard = "?";
		        private String msWhite = "?";
		        private String msScore = "?";
		        private String msBlack = "?";
		 
		// Properties.
		        public void setBoard(String sNumber){
		                msBoard = sNumber;
		        }
		        public String getBoard(){
		                return msBoard;
		        }
		        public void setWhite(String sName){
		                msWhite = sName;
		        }
		        public String getWhite(){
		                return msWhite;
		        }
		        public void setScore(String sScore){
		                msScore = sScore;
		        }
		        public String getScore(){
		                return msScore;
		        }
		        public void setBlack(String sName){
		                msBlack = sName;
		        }
		        public String getBlack(){
		                return msBlack;
		        }
		}
		 
		 
		private class PairTwoRankingRow{
		 
		// Member variables.
	        private String msNumber = "?";
	        private String msTitle ="?";
	        private String msName = "?";
	        private String msFRBERating = "?";
	        private String msScore = "?";
	        private String msGamesPlayed = "?";
	        private String msPerformance = "?";
		 
		// Properties.
	        public void setNumber(String sNumber){
                msNumber = sNumber;
	        }
	        public String getNumber(){
                return msNumber;
	        }
	        public void setTitle(String sTitle){
                msTitle = sTitle;
	        }
	        public String getTitle(){
                return msTitle;
	        }
	        public void setName(String sName){
                msName = sName;
	        }
	        public String getName(){
                return msName;
	        }
	        public void setFRBERating(String sValue){
	        	if (sValue.equals("0")) {
	        		msFRBERating = "n. c.";
	        	}
	        	else {
	        		msFRBERating = sValue;
	        	}
	        }
	        public String getFRBERating(){
                return msFRBERating;
	        }
	        public void setScore(String sValue){
                msScore = sValue;
	        }
	        public String getScore(){
                return msScore;
	        }
	        public void setGamesPlayed(String sValue){
                msGamesPlayed = sValue;
	        }
	        public String getGamesPlayed(){
                return msGamesPlayed;
	        }
	        public void setPerformance(String sValue){
                msPerformance = sValue;
	        }
	        public String getPerformance(){
                return msPerformance;
	        }
		}
		 
		private class PairTwoResults {
	        private ArrayList<PairTwoRankingRow> mptrarlstRanking = null;
	        private ArrayList<Round> mlstrndRound = null;
	       
	        public PairTwoResults(ArrayList<PairTwoRankingRow> lstptrarRa, ArrayList<Round> lstrndRound) {
                mptrarlstRanking = lstptrarRa;
                mlstrndRound = lstrndRound;
	        }
	       
	        public ArrayList<PairTwoRankingRow> getRanking() {
                return mptrarlstRanking;
	        }
	        public ArrayList<Round> getRounds() {
                return mlstrndRound;
	        }
		}
	
	
	
/********************
* MEMBER VARIABLES *
********************/
        private Map<String, String> mmapFirstName = new HashMap<String, String>(){{
            put("Anais", "Anaïs");
    		put("Andre", "André");
            put("Benoit", "Benoît");
            put("Cedric", "Cédric");
            put("Cesarino", "Césarino");
            put("Francois", "François");
            put("Gaetan", "Gaëtan");
            put("Jerome", "Jérôme");
            put("Michael", "Michaël");
            put("Regis", "Régis");
            put("Sebastien", "Sébastien");
            put("Severino", "Sévérino");
        }};
 
        private Map<String, String> mmapCompoundName = new HashMap<String, String>(){{
                put("De Gaspari Raymond", "Raymond De Gaspari");
        }};
 
		/***********
		* METHODS *
		***********/
        /**
         * Try to spot first name, last name and to capitalize them correctly, after inverting them.
         * @param sFullName
         * @return
         */
        private String formatName(String sFullName) {
            String sReturnValue= sFullName;
           
            
            char[] acFullName = sFullName.toLowerCase().toCharArray();
            
            
            // REMOVE EVENTUAL TRAILING 'M' or 'F'.
            int nRealEndOfFullName=acFullName.length-1;
            if (nRealEndOfFullName>2 && (acFullName[nRealEndOfFullName]=='m' || acFullName[nRealEndOfFullName]=='f')) {
            	while (Character.isWhitespace(acFullName[--nRealEndOfFullName])) {
            	}
            }
            
            // CAPITALIZE.
            char cCurrent= '\0';
            boolean bPreviousCharIsNoLetter = true;
            for (int nI=0; nI < nRealEndOfFullName+1; nI++) {
                cCurrent = acFullName[nI];
                if (bPreviousCharIsNoLetter && Character.isLetter(cCurrent)) {
                  acFullName[nI] = Character.toUpperCase(cCurrent);
                  bPreviousCharIsNoLetter = false;
                } else if (Character.isWhitespace(cCurrent) || cCurrent=='-' || cCurrent=='\'') {
                            bPreviousCharIsNoLetter = true;
                }
            }
 
            // INVERT LAST AND FIRST NAME.
            String[] asWord = (String.valueOf(acFullName, 0, nRealEndOfFullName+1)).split(" ");
            if (asWord.length == 2) {
                    // Add accent.
                    if (mmapFirstName.containsKey(asWord[1])) {
                    	asWord[1] = mmapFirstName.get(asWord[1]);
                    }
                    sReturnValue = asWord[1] + " " + asWord[0];
            }
            else {  /* Occurs seldom. */
                    if (mmapCompoundName.containsKey(String.valueOf(acFullName, 0, nRealEndOfFullName+1))) {
                        sReturnValue = mmapCompoundName.get(String.valueOf(acFullName, 0, nRealEndOfFullName+1));
                    }
                    else {
                        sReturnValue = "<!-- UNIDENTIFIED NAMES -->" + String.valueOf(acFullName);
                    }
            }
            return sReturnValue;
        }
 
        /**
         *
         * @param sFilePath
         * @return
         */
        public PairTwoResults importResults(String sFilePath) {
 

    		// VALUES TO BE RETURNED.
            ArrayList<PairTwoRankingRow> lstrarRanking = null;
            ArrayList<Round> lstrndRound = null;
        	
/*            // COMPUTE DATE OF LAST FRIDAY.
            Calendar dtCurrentDay = Calendar.getInstance();
            while (dtCurrentDay.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY ) {
                    dtCurrentDay.add(Calendar.DATE,-1) ;
            }
            String sLastFriday = (new SimpleDateFormat("d MMMMM yyyy")).format(dtCurrentDay.getTime()) ;
*/
            
            // READ FILE.
            try {
            	char cFirstChar = '\0';
                String[] asWord = null;
                PairTwoRankingRow ptrarCurrentRankingRow = null;
                lstrndRound = new ArrayList<Round>();
//                Round rndCurrentRound = null;
                int nCurrentRound = -1;
                PairTwoGame ptgCurrentGame = null;
            	BufferedReader brResults  = new BufferedReader(new InputStreamReader(new FileInputStream(sFilePath),"ISO-8859-1"));
//            		BufferedReader brResults = new BufferedReader(new FileReader(sFilePath));
                String sLine = brResults.readLine();
                while (sLine != null) {
                	if (sLine.length()!=0) {
                		
                		// EXAMINE START OF LINE.
                        if (sLine.startsWith("Ronde  ")) {
                        	// Prepare to receive game details.
                    		nCurrentRound++;	// New Round (often several per result sheet).
                    		asWord=sLine.split(";",-1);
                    		try {
                            	lstrndRound.add(new Round((new SimpleDateFormat("dd-MM-yyyy")).parse(asWord[1])));
                    		}
                    		catch (ParseException e) {
                    			e.printStackTrace();
                    		}		
                        }
                        else if (sLine.startsWith("Classement ")) {
                            // Prepare to receive ranking.
                        	lstrarRanking = new ArrayList<PairTwoRankingRow>();	// New ranking (only one per results sheet)
                        }
                        else if (nCurrentRound != -1) {
                        	// if prepared to received game details...
                    		if (!sLine.startsWith("-")) {
                    			asWord = sLine.split(";",-1);
                                ptgCurrentGame = new PairTwoGame();
                                ptgCurrentGame.setBoard(asWord[0].trim());
                                ptgCurrentGame.setWhite(formatName(asWord[1]));
                                if (asWord[2].trim().equals("Bye")) {
                                    ptgCurrentGame.setScore("1-0");
                                    ptgCurrentGame.setBlack("bye");
                                }
                                else {
                                    ptgCurrentGame.setScore(asWord[2].trim());
                                    ptgCurrentGame.setBlack(formatName(asWord[3]));
                                }
                                lstrndRound.get(nCurrentRound).add(ptgCurrentGame);
//	                                    lstrndRound.get(nCurrentRound).setDate(sLastFriday);
                            }
                        }
                        else if (lstrarRanking != null) {
                        	// if prepared to receive ranking..
                        	cFirstChar=sLine.charAt(0);
                        	if (cFirstChar>='0' && cFirstChar<='9') {
                        	
	                            // Create and fill ranking row.
	                            asWord = sLine.split(";",-1);
	                            ptrarCurrentRankingRow = new PairTwoRankingRow();
	                            ptrarCurrentRankingRow.setNumber(asWord[0].trim());
	                            ptrarCurrentRankingRow.setTitle(asWord[1].trim());
	                            ptrarCurrentRankingRow.setName(formatName(asWord[2]));
	                            ptrarCurrentRankingRow.setFRBERating(asWord[3].trim());
	                            // Analyze score/total games played.
	                            char[] acScore;
	                            int nEndOfWord2;
	                            int nBeginOfWord2;
	                            int nEndOfWord1;
	                            int nBeginOfWord1;
	                            // Split string in 2 substrings on '/' and replace '.' with ','.
	                            acScore = asWord[7].toCharArray();
	                            nEndOfWord2 = -1;
	                            nBeginOfWord2 = -1;
	                            nEndOfWord1 = -1;
	                            nBeginOfWord1 = -1;
	                            int nI=acScore.length;
	                            while (--nI>-1 && (acScore[nI]==' '));       //Trim trailing spaces.
	                            nEndOfWord2=nI;
	                            while (--nI>-1 && acScore[nI]!=' ' && acScore[nI]!='/');
	                            nBeginOfWord2=nI+1;
	                            while (--nI>-1 && acScore[nI]==' ');
	                            if (acScore[nI]=='/') {     // Ignore slash.
	                                while (--nI>-1 && acScore[nI]==' ');
	                            }
	                            if (acScore[nI]=='0') {
	                                nI-=2;
	                            }
	                            else {
	                                if (nI>-1) {
                                        acScore[nI-1]=',';  // Replace '.' by ','.
	                                }
	                            }
	                            nEndOfWord1=nI;
	                            while (--nI>-1 && acScore[nI]!=' ');        // Trim heading spaces.
	                            nBeginOfWord1=nI+1;
	                            ptrarCurrentRankingRow.setScore(new String(acScore, nBeginOfWord1, nEndOfWord1-nBeginOfWord1+1));
	                            ptrarCurrentRankingRow.setGamesPlayed(new String(acScore, nBeginOfWord2, nEndOfWord2-nBeginOfWord2+1));
                        		// Add ranking row.
	                            lstrarRanking.add(ptrarCurrentRankingRow);
	                        }
                    	}
                	}
                        sLine = brResults.readLine();
                    }
	                brResults.close();
            }
            catch(IOException e){   
                System.err.println("Ouverture du fichier impossible\n");
	          }
            finally {
//                    sLine = null;
//                    asWord = null;
//                    asSplitted = null;
//                    lstrarRa = null;
//                    ptrarCurrentRaR = null;
//                    lstlstptgRo = null;
//                    lstptgCurrentRound = null;
 
 
            }
            return (new PairTwoResults(lstrarRanking, lstrndRound));
        }

        public void outputResults(PairTwoResults myPTRe, String sFilePath) {
        	 
            StringBuilder sbResults = new StringBuilder();
            ArrayList<PairTwoRankingRow> mlstptrarRanking = myPTRe.getRanking();
            ArrayList<Round> lstrndRound;
            int nRoundCount;
            Round rndCurrentRound =null;
            // Ranking.
            sbResults.append("&nbsp;\n<h1>Classement après la ronde ");
            sbResults.append(myPTRe.getRounds().size());
            sbResults.append("</h1>\n<table>\n");
            if (mlstptrarRanking!=null) {
	            for (int nI=0; nI < mlstptrarRanking.size(); nI++) {
	            	sbResults.append("<tr><td style=\"text-align: right;\">");
                    sbResults.append(mlstptrarRanking.get(nI).getNumber());
                    sbResults.append("</td><td style=\"text-align: left;\">");
                    sbResults.append(mlstptrarRanking.get(nI).getName());
                    sbResults.append(" (");
                    sbResults.append(mlstptrarRanking.get(nI).getFRBERating());
                    sbResults.append(")</td><td style=\"text-align: left;\">");
                    sbResults.append(mlstptrarRanking.get(nI).getScore());
                    sbResults.append("</td></tr>\n");
	            }
            }
            sbResults.append("</table>\n");
            // Rounds.
            lstrndRound = myPTRe.getRounds();
            nRoundCount = lstrndRound.size();
            for (int nI=0; nI < nRoundCount; nI++) {
                rndCurrentRound = lstrndRound.get(nI);
                sbResults.append("\n");
                sbResults.append("&nbsp;\n<h1>Ronde ");
                sbResults.append(Integer.toString(nI+1));
                sbResults.append("</h1>\n");
                sbResults.append((new SimpleDateFormat("d MMMMM yyyy")).format(rndCurrentRound.getDate()));
                sbResults.append("\n<table>\n");
                for (int nJ=0; nJ < rndCurrentRound.size(); nJ++) {
                	sbResults.append("<tr><td style=\"text-align: right;\">");
                    sbResults.append(rndCurrentRound.get(nJ).getBoard());
                    sbResults.append("</td><td style=\"text-align: right;\">");
                    sbResults.append(rndCurrentRound.get(nJ).getWhite());
                    sbResults.append("</td><td style=\"text-align: center;\">");
                    sbResults.append(rndCurrentRound.get(nJ).getScore());
                    sbResults.append("</td><td style=\"text-align: left;\">");
                    sbResults.append(rndCurrentRound.get(nJ).getBlack());
                    sbResults.append("</td></tr>\n");
                }
                sbResults.append("</table>\n");
            }
     
            // Write to file.
            try{
                    File fResults=new File(sFilePath);
                    fResults.createNewFile();
                    FileWriter fwResults=new FileWriter(fResults);
                    fwResults.write(sbResults.toString());
                    fwResults.close();
            }
            catch(IOException e){   
              System.err.println("Erreur, écriture impossible\n");
            }
            finally {
            }
     
    }       
        // DESTRUCTOR.
        public void finalize() {
                // TODO: I don't free sublists.
//                lstrarRa = null;
//                ptrarCurrentRaR = null;
//                lstlstptgRo = null;
//                lstptgCurrentRo = null;
        }
 
       
       
}

