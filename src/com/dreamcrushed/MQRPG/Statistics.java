/*
 * This file is part of MineQuest, The ultimate MMORPG plugin!.
 * MineQuest is licensed under GNU General Public License v3.
 * Copyright (C) 2012 The MineQuest Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dreamcrushed.MQRPG;

import java.util.List;

import org.bukkit.event.Listener;

import com.alta189.simplesave.Database;
import com.alta189.simplesave.exceptions.TableRegistrationException;
import com.alta189.simplesave.query.QueryResult;
import com.theminequest.MineQuest.API.Tracker.Statistic;

public class Statistics implements Listener {

	private Database backend;
	
	public Statistics(Database backend) {
		this.backend = backend;
	}

	public Database getStorageBackend() {
		return backend;
	}

	@SuppressWarnings("unchecked")
	public <T extends Statistic> List<T> getAllStatistics(String playerName, Class<? extends Statistic> tableClazz) {
		List<? extends Statistic> result = backend.select(tableClazz).where().equal("playerName", playerName.toLowerCase()).execute().find();
		for (Statistic s : result)
			s.setup();
		return (List<T>) result;
	}

	public <T extends Statistic> void saveStatistic(T statistic,	Class<? extends Statistic> tableClazz) {
		backend.save(tableClazz,statistic);
	}

	public void registerStatistic(Class<? extends Statistic> tableClazz) {
		try {
			backend.registerTable(tableClazz);
		} catch (TableRegistrationException e) {
			throw new RuntimeException(e);
		}
	}

	public <T extends Statistic> List<T> getStatisticList(Class<? extends Statistic> tableClazz) {
		QueryResult<? extends Statistic> r = backend.select(tableClazz).execute();
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) r.find();
		return list;
	}

	public <T extends Statistic> T createStatistic(String playerName, Class<? extends Statistic> tableClazz) {
		try {
			@SuppressWarnings("unchecked")
			T s = (T) tableClazz.newInstance();
			s.setPlayerName(playerName);
			s.setup();
			return s;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public <T extends Statistic> void removeStatistic(T statistic, Class<? extends Statistic> tableClazz) {
		backend.remove(tableClazz, statistic);
	}

//	public <T extends QuestStatistic> T getQuestStatistic(String playerName, String questName, Class<? extends QuestStatistic> tableClazz) {
//		@SuppressWarnings("unchecked")
//		T s = (T) backend.select(tableClazz).where().equal("playerName", playerName.toLowerCase()).and().equal("questName", questName).execute().findOne();
//		if (s != null)
//			s.setup();
//		return s;
//	}
}
