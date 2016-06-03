package nega.tbom.baseobjects;

import nega.tbom.objects.Player;

public interface Collision {
	public boolean onCollide(CollidableObject obj);

	public boolean onCollide(Player obj);
}
