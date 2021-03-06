// This file is part of the 'texasholdem' project, an open source
// Texas Hold'em poker application written in Java.
//
// Copyright 2009 Oscar Stigter
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.mario.pokermaster.util;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.example.mario.pokermaster.actions.Action;

public class Player implements IPlayer{

	/** Name. */
	private final String name;

	/** Hand of cards. */
	private final Hand hand;

	/** Current amount of cash. */
	private int cash;

	/** Whether the player has hole cards. */
	private boolean hasCards;

	/** Current bet. */
	private int bet;

	/** Last action performed. */
	private Action action;

	public Player(String name, int cash) {
		this.name = name;
		this.cash = cash;

		hand = new Hand();

		resetHand();
	}

	/**
	 * Prepares the player for another hand.
	 */
	public void resetHand() {
		hasCards = false;
		hand.removeAllCards();
		resetBet();
	}

	/**
	 * Resets the player's bet.
	 */
	public void resetBet() {
		bet = 0;
		action = (hasCards() && cash == 0) ? Action.ALL_IN : null;
	}

	/**
	 * Sets the hole cards.
	 */
	public void setCards(List<Card> cards) {
		hand.removeAllCards();
		if (cards != null) {
			if (cards.size() == 2) {
				hand.addCards(cards);
				hasCards = true;
				System.out.format("%s's cards: %s\n", name, hand);
			} else {
				throw new IllegalArgumentException("Invalid number of cards");
			}
		}
	}

	/**
	 * Returns whether the player has his hole cards dealt.
	 * 
	 * @return True if the hole cards are dealt, otherwise false.
	 */
	public boolean hasCards() {
		return hasCards;
	}

	/**
	 * Returns the player's name.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player's current amount of cash.
	 * 
	 * @return The amount of cash.
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * Returns the player's current bet.
	 * 
	 * @return The current bet.
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * Sets the player's current bet.
	 * 
	 * @param bet
	 *            The current bet.
	 */
	public void setBet(int bet) {
		this.bet = bet;
	}

	/**
	 * Returns the player's most recent action.
	 * 
	 * @return The action.
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Sets the player's most recent action.
	 * 
	 * @param action
	 *            The action.
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Indicates whether this player is all-in.
	 * 
	 * @return True if all-in, otherwise false.
	 */
	public boolean isAllIn() {
		return hasCards() && (cash == 0);
	}

	/**
	 * Returns the player's hole cards.
	 * 
	 * @return The hole cards.
	 */
	public Card[] getCards() {
		return hand.getCards();
	}

	/**
	 * Posts the small blind.
	 * 
	 * @param blind
	 *            The small blind.
	 */
	public void postSmallBlind(int blind) {
		action = Action.SMALL_BLIND;
		cash -= blind;
		bet += blind;
	}

	/**
	 * Posts the big blinds.
	 * 
	 * @param blind
	 *            The big blind.
	 */
	public void postBigBlind(int blind) {
		action = Action.BIG_BLIND;
		cash -= blind;
		bet += blind;
	}

	/**
	 * Pays an amount of cash.
	 * 
	 * @param amount
	 *            The amount of cash to pay.
	 */
	public void payCash(int amount) {
		if (amount > cash) {
			throw new IllegalStateException("Player asked to pay more cash than he owns!");
		}
		cash -= amount;
	}

	/**
	 * Wins an amount of money.
	 * 
	 * @param amount
	 *            The amount won.
	 */
	public void win(int amount) {
		cash += amount;
	}

	/**
	 * Returns a clone of this player with only public information.
	 * 
	 * @return The cloned player.
	 */
	public Player publicClone() {
		Player clone = new Player(name, cash);
		clone.hasCards = hasCards;
		clone.bet = bet;
		clone.action = action;
		return clone;
	}
	
	@Override
	public Action act(int minBet, int bet, Set<Action> allowedActions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action act(int minBet, int bet, Set<Action> allowedActions, Hand board) {
		return null;
	}

	public void showActions(Set<Action> allowedActions) {
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int doBet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doRaise() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Hand getHand() {
		return hand;
	}
}
