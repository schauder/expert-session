/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.thorbenexpertsession;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public class Minion {

	@Id
	UUID id;
	String name;
	private Set<Gadget> gadgets = new HashSet<>();
	AggregateReference<Person, Long> master;


	@PersistenceCreator
	Minion(UUID id, String name) {

		this.id = id;
		this.name = name;
	}

	Minion(String name) {
		this.id = null;
		this.name = name;
	}

	void setMaster(Person master) {
		this.master = AggregateReference.to(master.id());
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Minion minion = (Minion) o;
		return Objects.equals(id, minion.id) && Objects.equals(name, minion.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Minion{" +
				"id=" + id +
				", name='" + name + '\'' +
				", gadgets=" + gadgets +
				", master=" + master + '\'' +
				'}';
	}

	void add(String gadget) {
		gadgets.add(new Gadget(gadget));
	}

	void forEachGadget(Consumer<Gadget> consumer) {
		gadgets.forEach(consumer);
	}
}
