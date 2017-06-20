/* 
 *  Autor:   Anderson Hiroshi de Siqueira 
 *  N USP:   9313197
 *  Subject: OOP - SCC0504 
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */

package deck;

public enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
	EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

	public int value;
	
	Rank(int value) {
		this.value = value;
	}
	
	int value() {
		return value;
	}

}
