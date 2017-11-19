using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Scripts.Behaviours
{
    public interface IPlayerBehaviour
    {
        void handler(object sender, EventArgs e);
    }
}