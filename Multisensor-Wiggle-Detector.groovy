/**
 *  Multisensor Wiggle Detector
 *  Attach this to your multisensor and look in the debug log for changes
 */

definition(
    name: "Multisensor Wiggle Detector",
    namespace: "n8xd",
    author: "n8xd",
    description: "Wiggler",
    category: "SmartThings Labs",
    iconUrl: "https://raw.githubusercontent.com/n8xd/Wiggle/master/wiggle.gif",
    iconX2Url: "https://raw.githubusercontent.com/n8xd/Wiggle/master/wiggle2x.gif"
)

preferences {

        section("Use the orientation of this sensor") {
            input "cube", "capability.threeAxis", required: false, title: "SmartSense Multi sensor"
        }
}


def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}

def initialize() {
    subscribe(cube, "threeAxis",positionHandler)
}

def positionHandler(evt) {
   getOrientation(evt.xyzValue)
}



private getOrientation(xyz=null) {
    def value = xyz ?: cube.currentValue("threeAxis")    
    log.debug "${value.x}, ${value.y}, ${value.z}"
}
