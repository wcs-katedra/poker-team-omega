/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.strategy;

import com.wcs.poker.gamestate.Card;
import java.util.List;

/**
 *
 * @author Timi
 */
public class CombinationChecker {
    public boolean isBigPair(Card card1, Card card2) {
        return card1.isRankEqual(card2) && card1.rankMatches("A|K|Q|J|[7-9]");
    }
    
    public boolean isAceAndQueen(Card card1, Card card2) {
        return (card1.isRank("A") && card2.isRank("Q")) || (card1.isRank("Q") && card2.isRank("A"));
    }
    
    public boolean isSmallPair(Card card1, Card card2) {
        return card1.isRankEqual(card2) && card1.rankMatches("[2-6]");
    }
}
