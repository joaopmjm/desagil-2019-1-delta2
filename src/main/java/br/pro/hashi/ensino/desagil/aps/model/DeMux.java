package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate top1;
    private final NandGate top2;
    private final NandGate mid;
    private final NandGate b1;
    private final NandGate b2;

    public DeMux() {
        super("DeMux", 2, 2);
        top1 = new NandGate();
        top2 = new NandGate();
        mid = new NandGate();
        b1 = new NandGate();
        b2 = new NandGate();

        top1.connect(1, mid);
        top2.connect(0, top1);
        top2.connect(1, top1);
        b2.connect(0, b1);
        b2.connect(1, b1);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin > 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        switch (outputPin) {
            case 0:
                return top2.read();
            case 1:
                return b2.read();
            default:
                throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                top1.connect(0, emitter);
                b1.connect(1, emitter);
                break;
            case 1:
                mid.connect(0, emitter);
                mid.connect(1, emitter);
                b1.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);

        }
    }
}
