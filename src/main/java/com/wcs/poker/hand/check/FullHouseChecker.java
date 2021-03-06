/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.hand.check;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.hand.HandRank;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Timi
 */
public class FullHouseChecker implements HandChecker {
private static final HandRank HAND_RANK=HandRank.FULL_HOUSE;
    private List<List<Card>> ranksCounter= new LinkedList<>();
    private List<Card> handCards = new LinkedList<>();

    @Override
    public boolean check(List<Card> cards) {
        RanksMap(cards);
        SortByMaxRank();
        handCards.addAll(ranksCounter.get(0));
        handCards.addAll(ranksCounter.get(1));
        if(handCards.size()<5)
            handCards.clear();
        else
            handCards=handCards.subList(0, 5);
        return (!handCards.isEmpty());
    }

    @Override
    public List<Card> getResult() {
        return handCards;
    }

    private void RanksMap(List<Card> cards) {
        for (Card card : cards) {
            final String rank = card.getRank();
            List<Card> rankCards = null;
            for (List<Card> ranksCounter1 : ranksCounter) {
                if(card.isRankEqual(ranksCounter1.get(0)))rankCards=ranksCounter1;
            }
            if (rankCards == null) {
                rankCards = new LinkedList<>();
                ranksCounter.add(rankCards);
            }
            rankCards.add(card);
        }
    }

    private void SortByMaxRank() {
        Collections.sort(ranksCounter,new Comparator<List<Card>>(){
            @Override
            public int compare(List<Card> o1, List<Card> o2) {
                if(o1.size()==o2.size()){
                    return new CardComporatorDecending().compare(o1.get(0), o2.get(0));
                }
                return o2.size()-o1.size();
            }        
        });
    }

    @Override
    public HandRank getRank() {
        return HAND_RANK;
    }    
}
