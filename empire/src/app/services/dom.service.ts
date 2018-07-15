import { Injectable, Injector, ComponentFactoryResolver, EmbeddedViewRef, ApplicationRef } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DomService {

  private childComponentRef: any;

  constructor(private _componentFactoryResolver: ComponentFactoryResolver,
    private _appRef: ApplicationRef, private _injector: Injector) { }

    public appendComponentTo(parentId: string, child: any, childConfig?: ChildConfig) {
      // Create a component reference from the component
      const childComponentRef = this._componentFactoryResolver
        .resolveComponentFactory(child)
        .create(this._injector);
      // Attach the config to the child (inputs and outputs)
      this.attachConfig(childConfig, childComponentRef);
      this.childComponentRef = childComponentRef;
      // Attach component to the appRef so that it's inside the ng component tree
      this._appRef.attachView(childComponentRef.hostView);
      // Get DOM element from component
      const childDomElem = (childComponentRef.hostView as EmbeddedViewRef<any>)
        .rootNodes[0] as HTMLElement;
      // Append DOM element to the body
      document.getElementById(parentId).appendChild(childDomElem);
    }

    public removeComponent() {
      this._appRef.detachView(this.childComponentRef.hostView);
      this.childComponentRef.destroy();
    }

    private attachConfig(config, componentRef) {
      const inputs = config.inputs;
      const outputs = config.outputs;
      for (const key in inputs) {
        if (key != null) {
          componentRef.instance[key] = inputs[key];
        }
      }
      for (const key in outputs) {
        if (key != null) {
          componentRef.instance[key] = outputs[key];
        }
      }
    }
}

interface ChildConfig {
  inputs: object;
  outputs: object;
}
