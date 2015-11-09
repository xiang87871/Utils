package AngleUtil;

public class AngleUtil {

	/**
	 * @param v1 向量v1
	 * @param v2 向量v2
	 * @return 向量夹角
	 * 
	 * 向量1 逆时针旋转到向量2 的角度
	 * getAngle(v1,v2) != getAngle(v2,v1)
	 */
	public static double getReverseClockRotationAngle(Vector v1, Vector v2) {
		double av1 = Math.atan2(v1.y, v1.x);
		double av2 = Math.atan2(v2.y, v2.x);
		if(av1 < 0) {
			av1 = 2*Math.PI+av1;
		}
		if(av2 < 0) {
			av2 = 2*Math.PI+av2;
		}
		double angle = (av2-av1) * (180 / Math.PI);
		angle = angle >= 0?angle:360 + angle;
		return angle;
	}

	/**
	 * @param v1 向量v1
	 * @param v2 向量v2
	 * @return 向量夹角
	 * 
	 * 向量1 顺时针旋转到向量2 的角度
	 * getAngle(v1,v2) != getAngle(v2,v1)
	 */
	public static double getClockRotationAngle(Vector v1, Vector v2) {
		double reverseClockRotationAngle = getReverseClockRotationAngle(v1, v2);
		return reverseClockRotationAngle == 0 ? 0 : 360-reverseClockRotationAngle;
		
	}
}
