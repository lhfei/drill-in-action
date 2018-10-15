/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.drill;

import java.util.Random;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 15, 2018
 */

public class TRandom {

	public static void main(String[] args) {
		int min = 0, max = 5;
		Random random = new Random();
		
		for (int i = 0; i < 100; i++) {
			int data = random.nextInt(max - min + 1) + min;
			
			System.out.format("Random data: %d", data);
			System.out.println();
			
		}

	}

}
