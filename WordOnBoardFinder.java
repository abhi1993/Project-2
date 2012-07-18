/**
 * Copyright (C) 2002 Michael Green <mtgreen@cs.ucsd.edu>
 * 
 * Copyright (C) 2002 Paul Kube <kube@cs.ucsd.edu>
 * 
 * Copyright (C) 2005 Owen Astrachan <ola@cs.duke.edu>
 * 
 * Copyright (C) 2011 Hoa Long Tam <hoalong.tam@berkeley.edu> and Armin Samii
 * <samii@berkeley.edu>
 * 
 * This file is part of CS Boggle.
 * 
 * CS Boggle is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * CS Boggle is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * CS boggle. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordOnBoardFinder {

  /**
	 * Return a list of cells on the given board such that the i-th element of the
	 * list corresponds to the i-th character of the string as found on the board.
	 * Returns an empty list if the word cannot be found on the board.
	 * 
	 * @param board
	 *          is searched for the given word
	 * @param word
	 *          is being searched for on the board
	 * @return list of cells on the supplied board that correspond to the word, an
	 *         empty list should be returned if the word cannot be found on the
	 *         board
	 */
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		boolean[][] visited=new boolean[board.size()][board.size()];
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				String specialCase="";
				if(word.length()>1&&word.substring(0,2).equals("qu")){
					specialCase="qu";
				}

				if(board.getFace(r, c).equals(word.substring(0,1))||board.getFace(r, c).equals(specialCase)){
					list.add(new BoardCell(r,c));
					visited[r][c]=true;
					String nextWord="";

					if(word.length()>1&&word.substring(0,2).equals("qu")){
						nextWord=word.substring(2);
					}
					else{
						nextWord=word.substring(1);
					}

					if(cellsForWordHelper(board,nextWord,visited,list,r,c)){
						return list;
					}
					else{
						clearArray(visited);
						list.clear();
					}
				}

			}
		}
		return list;
	}

	public boolean cellsForWordHelper(BoggleBoard board, String word, boolean[][] visited,List <BoardCell>ls,int r,int c){
		boolean done=false;
		if(word.equals("")){
			return true;
		}

		else{
			for(int i=r-1;i<=r+1;i++){
				for(int j=c-1;j<=c+1;j++){
					if(board.isInBounds(i, j)){
						String specialCase="";
						String face=board.getFace(i, j);
						if(word.length()>1&&word.substring(0,2).equals("qu")){
							specialCase="qu";
						}


						if(!visited[i][j]&&(word.substring(0,1).equals(face)||face.equals(specialCase))){ 
							ls.add(new BoardCell(i,j));
							visited[i][j]=true;
							String nextWord="";

							if(word.length()>=2&&word.substring(0,2).equals("qu")){
								nextWord=word.substring(2);
							}
							else{
								nextWord=word.substring(1);
							}

							if(cellsForWordHelper(board,nextWord,visited,ls,i,j)){
								done=true;
								break;
							}
							else{
								visited[i][j]=false;
							}
						}
					}
				}
				if(done)
					return true;
			}
			visited[r][c]=false;
			ls.remove(ls.size()-1);
			return false;
		}
	}

	public void clearArray(boolean[][]arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				arr[i][j]=false;
			}
		}
	}


}
