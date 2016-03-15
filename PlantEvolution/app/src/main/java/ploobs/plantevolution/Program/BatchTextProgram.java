package ploobs.plantevolution.Program;

import android.content.Context;

import ploobs.plantevolution.GraphicFactory;
import ploobs.plantevolution.Program.Program;
import ploobs.plantevolution.R;
import ploobs.plantevolution.RawResourceReader;
import ploobs.plantevolution.Text.AttribVariable;

public class BatchTextProgram extends Program {

    private static final AttribVariable[] programVariables = {
            AttribVariable.A_Position, AttribVariable.A_TexCoordinate, AttribVariable.A_MVPMatrixIndex
    };


    @Override
    public void init() {
        Context localContext = GraphicFactory.getInstance().getGraphicContext();
        String frag = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_fragment_tex);
        String vert = RawResourceReader.readTextFileFromRawResource(localContext, R.raw.shader_vertex_text);

        super.init(vert, frag, programVariables);
    }

}