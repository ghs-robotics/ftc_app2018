package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.majora320.tealisp.evaluator.JavaInterface;
import org.majora320.tealisp.evaluator.LispException;
import org.majora320.tealisp.evaluator.LispObject;
import org.majora320.tealisp.evaluator.LispObject.Number;
import org.majora320.tealisp.evaluator.StackFrame;

public class MainInterface extends JavaInterface {
    @Override
    public boolean isSupportedFunction(String function) {
        Log.i("team-code", "Function: " + function);
        return "drive".equals(function);
    }

    private DriveSubsystem drive;

    public void setDrive(DriveSubsystem drive) {
        this.drive = drive;
    }

    @Override
    public LispObject runFunction(String name, LispObject[] params, StackFrame frame) throws LispException {
        Log.i("team-code", "name: " + name);
        switch(name) {
            case "drive":
                checkParams("drive", params,
                        new Class[]{ Number.class, Number.class, Number.class, Number.class}, false);

                double[] processed = new double[params.length];
                for (int i = 0; i < params.length; i++) {
                    LispObject param = params[i];
                    if (param instanceof LispObject.Integer) {
                        processed[i] = ((LispObject.Integer) param).value;
                    } else if (param instanceof LispObject.Double) {
                        processed[i] = ((LispObject.Double) param).value;
                    }
                }

                Log.i("team-code", "l: " + processed[0]);
                Log.i("team-code", "r: " + processed[1]);
                Log.i("team-code", "s: " + processed[2] + "\n");
                drive.setting("l", processed[0]);
                drive.setting("r", processed[1]);
                drive.setting("s", processed[2]);

                return new LispObject.Void();
            default:
                return null;
        }
    }
}
