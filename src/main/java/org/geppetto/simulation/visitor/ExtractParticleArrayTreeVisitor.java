/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2011 - 2015 OpenWorm.
 * http://openworm.org
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *     	OpenWorm - http://openworm.org/people.html
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
/**
 * Serialization class for nodes  
 *
 * @author  Jesus R. Martinez (jesus@metacell.us)
 */
package org.geppetto.simulation.visitor;

import java.util.ArrayList;
import java.util.List;
import org.geppetto.core.model.runtime.ParticleNode;
import org.geppetto.core.model.state.visitors.DefaultStateVisitor;
import org.geppetto.core.visualisation.model.Point;

public class ExtractParticleArrayTreeVisitor extends DefaultStateVisitor {

    List<Double> particles = new ArrayList<>();

	@Override
	public boolean visitParticleNode(ParticleNode node) {

		Point position = node.getPosition();

        double id = Double.parseDouble(node.getId().replaceAll("[^0-9]", ""));

        // super hack to deal with zeroth particle since zero is neither positive nor negative
        if (id == 0) {
            id = 0.5;
        }

        if (node.getKind() == 2.1f) {
            id *= -1;
        }

        particles.add(id);

        particles.add(position.getX());
        particles.add(position.getY());
        particles.add(position.getZ());

		return super.visitParticleNode(node);
	}

    public List<Double> getParticles() {
        return particles;
    }
}
