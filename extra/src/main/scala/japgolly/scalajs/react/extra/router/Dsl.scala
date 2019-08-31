package japgolly.scalajs.react.extra.router

import java.util.UUID
import java.util.regex.{Pattern, Matcher}
import scala.reflect.ClassTag
import scala.util.matching.Regex
import japgolly.scalajs.react.CallbackTo
import japgolly.scalajs.react.extra.internal.RouterMacros
import japgolly.scalajs.react.internal.identityFn
import japgolly.scalajs.react.vdom.VdomElement
import RouterConfig.Parsed

/**
 * This is not meant to be imported by library-users;
 * [[RouterConfigDsl]] is the entire library-user-facing facade & DSL.
 */
object StaticDsl {

  private val regexEscape1 = """([-()\[\]{}+?*.$\^|,:#<!\\])""".r
  private val regexEscape2 = """\x08""".r

  /**
   * Pattern.quote doesn't work in Scala.JS.
   *
   * http://stackoverflow.com/questions/2593637/how-to-escape-regular-expression-in-javascript
   */
  def regexEscape(s: String): String = {
    var r = s
    r = regexEscape1.replaceAllIn(r, """\\$1""")
    r = regexEscape2.replaceAllIn(r, """\\x08""")
    r
  }

  /**
   * Route builder. Allows you to specify routes like `"user" / int / "display"`.
   * Once complete, [[RouteB]] will become a [[Route]].
   */
  object RouteB {
    trait Composition[A, B] {
      type C
      val ga: C => A
      val gb: C => B
      val gc: (A, B) => C
      def apply(fa: RouteB[A], fb: RouteB[B]): RouteB[C] =
        new RouteB(
          fa.regex + fb.regex,
          fa.matchGroups + fb.matchGroups,
          g => for {a <- fa.parse(g); b <- fb.parse(i => g(i + fa.matchGroups))} yield gc(a, b),
          c => fa.build(ga(c)) + fb.build(gb(c)))
    }

    trait Composition_PriLowest {
      implicit def ***[A, B] = Composition[A, B, (A, B)](_._1, _._2, (_, _))
    }
    trait Composition_PriLow extends Composition_PriLowest {
      // Generated by bin/gen-router
      implicit def T3[A,B,C] = Composition[(A,B), C, (A,B,C)](r => (r._1,r._2), _._3, (l,r) => (l._1,l._2,r))
      implicit def T4[A,B,C,D] = Composition[(A,B,C), D, (A,B,C,D)](r => (r._1,r._2,r._3), _._4, (l,r) => (l._1,l._2,l._3,r))
      implicit def T5[A,B,C,D,E] = Composition[(A,B,C,D), E, (A,B,C,D,E)](r => (r._1,r._2,r._3,r._4), _._5, (l,r) => (l._1,l._2,l._3,l._4,r))
      implicit def T6[A,B,C,D,E,F] = Composition[(A,B,C,D,E), F, (A,B,C,D,E,F)](r => (r._1,r._2,r._3,r._4,r._5), _._6, (l,r) => (l._1,l._2,l._3,l._4,l._5,r))
      implicit def T7[A,B,C,D,E,F,G] = Composition[(A,B,C,D,E,F), G, (A,B,C,D,E,F,G)](r => (r._1,r._2,r._3,r._4,r._5,r._6), _._7, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,r))
      implicit def T8[A,B,C,D,E,F,G,H] = Composition[(A,B,C,D,E,F,G), H, (A,B,C,D,E,F,G,H)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7), _._8, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,r))
      implicit def T9[A,B,C,D,E,F,G,H,I] = Composition[(A,B,C,D,E,F,G,H), I, (A,B,C,D,E,F,G,H,I)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8), _._9, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,r))
      implicit def T10[A,B,C,D,E,F,G,H,I,J] = Composition[(A,B,C,D,E,F,G,H,I), J, (A,B,C,D,E,F,G,H,I,J)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9), _._10, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,r))
      implicit def T11[A,B,C,D,E,F,G,H,I,J,K] = Composition[(A,B,C,D,E,F,G,H,I,J), K, (A,B,C,D,E,F,G,H,I,J,K)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10), _._11, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,r))
      implicit def T12[A,B,C,D,E,F,G,H,I,J,K,L] = Composition[(A,B,C,D,E,F,G,H,I,J,K), L, (A,B,C,D,E,F,G,H,I,J,K,L)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11), _._12, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,r))
      implicit def T13[A,B,C,D,E,F,G,H,I,J,K,L,M] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L), M, (A,B,C,D,E,F,G,H,I,J,K,L,M)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12), _._13, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,r))
      implicit def T14[A,B,C,D,E,F,G,H,I,J,K,L,M,N] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M), N, (A,B,C,D,E,F,G,H,I,J,K,L,M,N)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13), _._14, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,r))
      implicit def T15[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N), O, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14), _._15, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,r))
      implicit def T16[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O), P, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15), _._16, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,r))
      implicit def T17[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P), Q, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15,r._16), _._17, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,l._16,r))
      implicit def T18[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q), R, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15,r._16,r._17), _._18, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,l._16,l._17,r))
      implicit def T19[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R), S, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15,r._16,r._17,r._18), _._19, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,l._16,l._17,l._18,r))
      implicit def T20[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S), T, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15,r._16,r._17,r._18,r._19), _._20, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,l._16,l._17,l._18,l._19,r))
      implicit def T21[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T), U, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15,r._16,r._17,r._18,r._19,r._20), _._21, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,l._16,l._17,l._18,l._19,l._20,r))
      implicit def T22[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V] = Composition[(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U), V, (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V)](r => (r._1,r._2,r._3,r._4,r._5,r._6,r._7,r._8,r._9,r._10,r._11,r._12,r._13,r._14,r._15,r._16,r._17,r._18,r._19,r._20,r._21), _._22, (l,r) => (l._1,l._2,l._3,l._4,l._5,l._6,l._7,l._8,l._9,l._10,l._11,l._12,l._13,l._14,l._15,l._16,l._17,l._18,l._19,l._20,l._21,r))
    }
    trait Composition_PriMed extends Composition_PriLow {
      implicit def _toA[A] = Composition[Unit, A, A](_ => (), identityFn, (_, a) => a)
      implicit def Ato_[A] = Composition[A, Unit, A](identityFn, _ => (), (a, _) => a)
    }
    object Composition extends Composition_PriMed {
      implicit def _to_ = Composition[Unit, Unit, Unit](_ => (), _ => (), (_, _) => ())

      type Aux[A, B, O] = Composition[A, B] {type C = O}

      def apply[A, B, O](a: O => A, b: O => B, c: (A, B) => O): Aux[A, B, O] =
        new Composition[A, B] {
          override type C = O
          val ga = a
          val gb = b
          val gc = c
        }
    }

    private val someUnit = Some(())

    def literal(s: String): RouteB[Unit] =
      new RouteB(regexEscape(s), 0, _ => someUnit, _ => s)

    val / = literal("/")
  }

  abstract class RouteCommon[R[X] <: RouteCommon[R, X], A] {

    def parseThen(f: Option[A] => Option[A]): R[A]

    /**
     * Prism map.
     *
     * Some values of `A` can be turned into a `B`s, some fail (in which case the route is considered non-matching).
     *
     * All `B`s can be turned back into `A`s.
     */
    def pmap[B](b: A => Option[B])(a: B => A): R[B]

    /**
     * Exponential map.
     *
     * Any `A` can be turned into a `B` and vice versa.
     */
    final def xmap[B](b: A => B)(a: B => A): R[B] =
      pmap(a => Some(b(a)))(a)

    final def filter(f: A => Boolean): R[A] =
      parseThen(_ filter f)

    final def mapParsed[B <: A](f: A => B): R[B] =
      xmap(f)(x => x)

    final def mapInput[B >: A](f: B => A): R[B] =
      xmap[B](x => x)(f)

    final def const[B](b: B)(implicit ev: A =:= Unit, ev2: Unit =:= A): R[B] =
      xmap(_ => b)(_ => ())
  }

  /**
   * A fragment of a route. Can be composed with other fragments.
   *
   * @param matchGroups The number of matches that `regex` will capture.
   */
  class RouteB[A](val regex: String,
                  val matchGroups: Int,
                  val parse: (Int => String) => Option[A],
                  val build: A => String) extends RouteCommon[RouteB, A] {
    import RouteB.Composition

    override def toString =
      s"RouteB($regex)"

    def ~[B](next: RouteB[B])(implicit c: Composition[A, B]): RouteB[c.C] =
      c(this, next)

    def /[B](next: RouteB[B])(implicit c: Composition[A, B]): RouteB[c.C] =
      this ~ RouteB./ ~ next

    override def parseThen(f: Option[A] => Option[A]): RouteB[A] =
      new RouteB(regex, matchGroups, f compose parse, build)

    override def pmap[B](b: A => Option[B])(a: B => A): RouteB[B] =
      new RouteB(regex, matchGroups, parse(_) flatMap b, build compose a)

    /**
     * Maps the captures values of the route to a case class.
     */
    def caseClass[B]: RouteB[B] =
      macro RouterMacros.quietCaseClassB[B]

    /**
     * Same as [[caseClass]] except the code generated by the macro is printed to stdout.
     */
    def caseClassDebug[B]: RouteB[B] =
      macro RouterMacros.debugCaseClassB[B]

    def option: RouteB[Option[A]] =
      new RouteB[Option[A]](s"($regex)?", matchGroups + 1,
        g => Some(if (g(0) eq null) None else parse(i => g(i + 1))),
        _.fold("")(build))

    final def route: Route[A] = {
      val p = Pattern.compile("^" + regex + "$")
      // https://github.com/scala-js/scala-js/issues/1727
      // val g = p.matcher("").groupCount
      // if (g != matchGroups)
      //   sys.error(s"Error in regex: /${p.pattern}/. Expected $matchGroups match groups but detected $g.")
      new Route(p, m => parse(i => m.group(i + 1)), a => Path(build(a)))
    }
  }

  class RouteBO[A](private val r: RouteB[Option[A]]) extends AnyVal {

    /**
     * Specify a default value when parsing.
     *
     * Note: Unlike [[withDefault()]] path generation will still explicitly include the default value.
     *
     * Eg. If the path is like "/file[.format]" and the default is JSON, "/file" will be read as "/file.json", but
     * when generating a path with JSON this will generate "/file.json" instead of "/file".
     */
    def parseDefault(default: => A): RouteB[A] =
      r.xmap(_ getOrElse default)(Some(_))

    /**
     * Specify a default value.
     *
     * Note: Unlike [[parseDefault()]] this will affect path generation too.
     *
     * Eg. If the path is like "/file[.format]" and the default is JSON, "/file" will be read as "/file.json", and
     * when generating a path with JSON this will generate "/file" instead of "/file.json".
     *
     * Make sure the type has a useful `.equals()` implementation.
     * Example: `default == default` should be `true`.
     */
    def withDefault(default: => A): RouteB[A] =
      r.xmap(_ getOrElse default)(a => if (default == a) None else Some(a))
  }

  /**
   * A complete route.
   */
  final class Route[A](pattern: Pattern,
                       parseFn: Matcher => Option[A],
                       buildFn: A => Path) extends RouteCommon[Route, A] {
    override def toString =
      s"Route($pattern)"

    override def parseThen(f: Option[A] => Option[A]): Route[A] =
      new Route(pattern, f compose parseFn, buildFn)

    override def pmap[B](b: A => Option[B])(a: B => A): Route[B] =
      new Route(pattern, parseFn(_) flatMap b, buildFn compose a)

    /**
     * Maps the captures values of the route to a case class.
     */
    def caseClass[B]: Route[B] =
      macro RouterMacros.quietCaseClass[B]

    /**
     * Same as [[caseClass]] except the code generated by the macro is printed to stdout.
     */
    def caseClassDebug[B]: Route[B] =
      macro RouterMacros.debugCaseClass[B]

    def parse(path: Path): Option[A] = {
      val m = pattern.matcher(path.value)
      if (m.matches)
        parseFn(m)
      else
        None
    }

    def pathFor(a: A): Path =
      buildFn(a)
  }

  // ===================================================================================================================

  object Rule {
    def parseOnly[Page](parse: Path => Option[Parsed[Page]]) =
      new Rule[Page](parse, _ => None, (_, _) => None)

    def empty[P]: Rule[P] =
      Rule(_ => None, _ => None, (_, _) => None)
  }

  /**
   * A single routing rule. Intended to be composed with other [[Rule]]s.
   * When all rules are composed, this is turned into a [[Rules]] instance.
   *
   * @param parse  Attempt to parse a given path.
   * @param path   Attempt to determine the path for some page.
   * @param action Attempt to determine the action when a route resolves to some page.
   * @tparam Page  The type of legal pages.
   */
  final case class Rule[Page](parse : Path         => Option[Parsed[Page]],
                              path  : Page         => Option[Path],
                              action: (Path, Page) => Option[Action[Page]]) {

    /**
     * Compose rules.
     */
    def |(that: Rule[Page]): Rule[Page] =
      new Rule[Page](
        parse  || that.parse,
        path   || that.path,
        (u, p) => (if (path(p).isDefined) action else that.action)(u, p))

    def xmap[A](f: Page => A)(g: A => Page): Rule[A] =
      new Rule[A](
        p => parse(p).map(_.bimap(_ map f, f)),
        path compose g,
        (u, p) => action(u, g(p)).map(_ map f))

    def pmap[W](f: Page => W)(pf: PartialFunction[W, Page]): Rule[W] =
      pmapF(f)(pf.lift)

    def pmapCT[W](f: Page => W)(implicit ct: ClassTag[Page]): Rule[W] =
      pmapF(f)(ct.unapply)

    def pmapF[W](f: Page => W)(g: W => Option[Page]): Rule[W] =
      new Rule[W](
        parse(_) map (_.bimap(_ map f, f)),
        g(_) flatMap path,
        (path, w) => g(w) flatMap (action(path, _)) map (_ map f))

    def widen[W >: Page](pf: PartialFunction[W, Page]): Rule[W] =
      widenF(pf.lift)

    def widenCT[W >: Page](implicit ct: ClassTag[Page]): Rule[W] =
      widenF(ct.unapply)

    def widenF[W >: Page](f: W => Option[Page]): Rule[W] =
      pmapF[W](p => p)(f)

    /** See [[autoCorrect()]]. */
    def autoCorrect: Rule[Page] =
      autoCorrect(Redirect.Replace)

    /**
     * When a route matches a page, compare its [[Path]] to what the route would generate for the same page and if they
     * differ, redirect to the generated one.
     *
     * Example: If a route matches `/issue/dev-23` and returns a `Page("DEV", 23)` for which the generate path would be
     * `/issue/DEV-23`, this would automatically redirect `/issue/dev-23` to `/issue/DEV-23`, and process
     * `/issue/DEV-23` normally using its associated action.
     */
    def autoCorrect(redirectMethod: Redirect.Method): Rule[Page] =
      new Rule(parse, path,
        (actualPath, page) =>
          path(page).flatMap(expectedPath =>
            if (expectedPath == actualPath)
              action(actualPath, page)
            else
              Some(RedirectToPath(expectedPath, redirectMethod))
          )
      )

    /**
     * Modify the path(es) generated and parsed by this rule.
     */
    def modPath(add: Path => Path, remove: Path => Option[Path]): Rule[Page] =
      new Rule(
        remove(_) flatMap parse,
        path(_) map add,
        action)

    /**
     * Add a prefix to the path(es) generated and parsed by this rule.
     */
    def prefixPath(prefix: String): Rule[Page] =
      modPath(
        p => Path(prefix + p.value),
        _ removePrefix prefix)

    /**
     * Add a prefix to the path(es) generated and parsed by this rule.
     *
     * Unlike [[prefixPath()]] when the suffix is non-empty, a slash is added between prefix and suffix.
     */
    def prefixPath_/(prefix: String): Rule[Page] = {
      val pre = Path(prefix)
      modPath(
        p => if (p.isEmpty) pre else pre / p,
        p => if (p.value == prefix) Some(Path.root) else p.removePrefix(prefix + "/"))
    }

    /**
     * Prevent this rule from functioning unless some condition holds.
     * When the condition doesn't hold, an alternative action may be performed.
     *
     * @param condUnmet Response when rule matches but condition doesn't hold.
     *                  If response is `None` it will be as if this rule doesn't exist and will likely end in the
     *                  route-not-found fallback behaviour.
     */
    def addCondition(cond: CallbackTo[Boolean])(condUnmet: Page => Option[Action[Page]]): Rule[Page] =
      addCondition(_ => cond)(condUnmet)

    /**
      * Prevent this rule from functioning unless some condition holds, passes in the page
      * requested as part of the context.
      * When the condition doesn't hold, an alternative action may be performed.
      *
      * @param cond Function that takes the requested page and returns true if the page should be rendered.
      * @param condUnmet Response when rule matches but condition doesn't hold.
      *                  If response is `None` it will be as if this rule doesn't exist and will likely end in the
      *                  route-not-found fallback behaviour.
      */
    def addCondition(cond: Page => CallbackTo[Boolean])(condUnmet: Page => Option[Action[Page]]): Rule[Page] =
      new Rule[Page](parse, path,
        (u, p) => if (cond(p).runNow()) action(u, p) else condUnmet(p))

    /**
     * Specify behaviour when a `Page` doesn't have an associated `Path` or `Action`.
     */
    def fallback(fp: Page => Path, fa: (Path, Page) => Action[Page]): Rules[Page] =
      new Rules[Page](parse, path | fp, action | fa)

    /**
     * When a `Page` doesn't have an associated  `Path` or `Action`, throw a runtime error.
     *
     * This is the trade-off for keeping the parsing and generation of known `Page`s in sync - compiler proof of
     * `Page` exhaustiveness is sacrificed.
     *
     * It is recommended that you call [[RouterConfig.verify]] as a sanity-check.
     */
    def noFallback: Rules[Page] =
      fallback(
        page         => sys error s"Unspecified path for page $page.",
        (path, page) => sys error s"Unspecified action for page $page at $path.")
  }

  object Rules {

    /**
     * Create routing rules all at once, with compiler proof that all `Page`s will have a `Path` and `Action`
     * associated.
     *
     * The trade-off here is that care will need to be taken to ensure that path-parsing aligns with paths
     * generated for pages. It is recommended that you call [[RouterConfig.verify]] as a sanity-check.
     */
    def apply[Page](toPage: Path => Option[Parsed[Page]], fromPage: Page => (Path, Action[Page])) =
      new Rules[Page](toPage, fromPage(_)._1, (_, p) => fromPage(p)._2)
  }

  /**
   * Exhaustive routing rules. For all `Page`s there are `Path`s and `Action`s.
   */
  final case class Rules[Page](parse : Path         => Option[Parsed[Page]],
                               path  : Page         => Path,
                               action: (Path, Page) => Action[Page]) {

    /**
     * Specify a catch-all response to unmatched/invalid routes.
     */
    def notFound(f: Path => Parsed[Page]): RouterConfig[Page] =
      RouterConfig.withDefaults(parse | f, path, action)
  }

  // ===================================================================================================================

  final class DynamicRouteB[Page, P <: Page, O](private val f: (P => Action[Page]) => O) extends AnyVal {
    def ~>(g: P => Action[Page]): O = f(g)
  }

  final class StaticRouteB[Page, O](private val f: (=> Action[Page]) => O) extends AnyVal {
    def ~>(a: => Action[Page]): O = f(a)
  }

  final class StaticRedirectB[Page, O](private val f: (=> Redirect[Page]) => O) extends AnyVal {
    def ~>(a: => Redirect[Page]): O = f(a)
  }

  final class DynamicRedirectB[Page, A, O](private val f: (A => Redirect[Page]) => O) extends AnyVal {
    def ~>(a: A => Redirect[Page]): O = f(a)
  }
}

// =====================================================================================================================
// =====================================================================================================================

object RouterConfigDsl {
  def apply[Page] =
    new BuildInterface[Page]

  class BuildInterface[Page] {
    def use[A](f: RouterConfigDsl[Page] => A): A =
      f(new RouterConfigDsl)

    def buildConfig(f: RouterConfigDsl[Page] => RouterConfig[Page]): RouterConfig[Page] =
      use(f)

    def buildRule(f: RouterConfigDsl[Page] => StaticDsl.Rule[Page]): StaticDsl.Rule[Page] =
      use(f)
  }
}

/**
 * DSL for creating [[RouterConfig]].
 *
 * Instead creating an instance of this yourself, use [[RouterConfigDsl.apply]].
 */
final class RouterConfigDsl[Page] {
  import StaticDsl.{Rule => _, Rules => _, _}

  type Action   = japgolly.scalajs.react.extra.router.Action[Page]
  type Renderer = japgolly.scalajs.react.extra.router.Renderer[Page]
  type Redirect = japgolly.scalajs.react.extra.router.Redirect[Page]
  type Parsed   = RouterConfig.Parsed[Page]

  // -------------------------------------------------------------------------------------------------------------------
  // Route DSL

  private def uuidRegex = "([A-Fa-f0-9]{8}(?:-[A-Fa-f0-9]{4}){3}-[A-Fa-f0-9]{12})"

  def root = Path.root
  val int  = new RouteB[Int] ("(-?\\d+)", 1, g => Some(g(0).toInt),           _.toString)
  val long = new RouteB[Long]("(-?\\d+)", 1, g => Some(g(0).toLong),          _.toString)
  val uuid = new RouteB[UUID](uuidRegex,  1, g => Some(UUID fromString g(0)), _.toString)

  private def __string1(regex: String): RouteB[String] =
    new RouteB(regex, 1, g => Some(g(0)), identityFn)

  /**
   * Matches a string.
   *
   * Best to use a whitelist of characters, eg. "[a-zA-Z0-9]+".
   * Do not capture groups; use "[a-z]+" instead of "([a-z]+)".
   * If you need to group, use non-capturing groups like "(?:bye|hello)" instead of "(bye|hello)".
   */
  def string(regex: String): RouteB[String] =
    __string1("(" + regex + ")")

  /** Captures the (non-empty) remaining portion of the URL path. */
  def remainingPath: RouteB[String] =
    __string1("(.+)$")

  /** Captures the (potentially-empty) remaining portion of the URL path. */
  def remainingPathOrBlank: RouteB[String] =
    __string1("(.*)$")

  implicit def _ops_for_routeb_option[A](r: RouteB[Option[A]]) = new RouteBO(r)

  implicit def _auto_routeB_from_str(l: String) = RouteB.literal(l)
  implicit def _auto_routeB_from_path(p: Path) = RouteB.literal(p.value)
  implicit def _auto_route_from_routeB[A, R](r: R)(implicit ev: R => RouteB[A]) = r.route

  // -------------------------------------------------------------------------------------------------------------------
  // Action DSL

  implicit def _auto_someAction[A <: Action](a: A): Option[A] = Some(a)

  def render[A](a: => A)(implicit ev: A => VdomElement): Renderer =
    Renderer(_ => a)

  def renderR[A](g: RouterCtl[Page] => A)(implicit ev: A => VdomElement): Renderer =
    Renderer(g(_))

  def dynRender[P <: Page, A](g: P => A)(implicit ev: A => VdomElement): P => Renderer =
    p => Renderer(_ => g(p))

  def dynRenderR[P <: Page, A](g: (P, RouterCtl[Page]) => A)(implicit ev: A => VdomElement): P => Renderer =
    p => Renderer(r => g(p, r))

  def redirectToPage(page: Page)(implicit method: Redirect.Method): RedirectToPage[Page] =
    RedirectToPage[Page](page, method)

  def redirectToPath(path: Path)(implicit method: Redirect.Method): RedirectToPath[Page] =
    RedirectToPath[Page](path, method)

  def redirectToPath(path: String)(implicit method: Redirect.Method): RedirectToPath[Page] =
    redirectToPath(Path(path))

  // -------------------------------------------------------------------------------------------------------------------
  // Rule building DSL

  type Rule = StaticDsl.Rule[Page]
  type Rules = StaticDsl.Rules[Page]
  def Rule = StaticDsl.Rule
  def emptyRule: Rule = Rule.empty

  implicit def _auto_parsed_from_redirect(r: Redirect): Parsed = Left(r)
  implicit def _auto_parsed_from_page    (p: Page)    : Parsed = Right(p)

  implicit def _auto_parsedO_from_parsed [A](p: A)        (implicit ev: A => Parsed): Option[Parsed] = Some(p)
  implicit def _auto_parsedO_from_parsedO[A](o: Option[A])(implicit ev: A => Parsed): Option[Parsed] = o.map(a => a)

  implicit def _auto_notFound_from_parsed [A](a: A)        (implicit ev: A => Parsed): Path => Parsed = _ => a
  implicit def _auto_notFound_from_parsedF[A](f: Path => A)(implicit ev: A => Parsed): Path => Parsed = f(_)

  implicit def _auto_routeParser_from_parsed  [A](a: A)                (implicit ev: A => Parsed): Path => Option[Parsed] = _ => Some(a)
  implicit def _auto_routeParser_from_parsedF [A](f: Path => A)        (implicit ev: A => Parsed): Path => Option[Parsed] = p => Some(f(p))
  implicit def _auto_routeParser_from_parsedO [A](o: Option[A])        (implicit ev: A => Parsed): Path => Option[Parsed] = _ => o.map(a => a)
  implicit def _auto_routeParser_from_parsedFO[A](f: Path => Option[A])(implicit ev: A => Parsed): Path => Option[Parsed] = f(_).map(a => a)

  // allows dynamicRoute ~~> X to not care if X is (Action) or (P => Action)
  implicit def _auto_pToAction_from_action(a: => Action): Page => Action = _ => a

  implicit def _auto_rules_from_rulesB(r: Rule): Rules = r.noFallback

  // Only really aids rewriteRuleR but safe anyway
  implicit def _auto_pattern_from_regex(r: Regex): Pattern = r.pattern

  /**
   * Note: Requires that `Page#equals()` be sensible.
   */
  def staticRoute(r: Route[Unit], page: Page): StaticRouteB[Page, Rule] = {
    val dyn = dynamicRoute(r const page){ case p if page == p => p }
    new StaticRouteB(a => dyn ~> a)
  }

  def dynamicRoute[P <: Page](r: Route[P])(pf: PartialFunction[Page, P]): DynamicRouteB[Page, P, Rule] =
    dynamicRouteF(r)(pf.lift)

  def dynamicRouteF[P <: Page](r: Route[P])(op: Page => Option[P]): DynamicRouteB[Page, P, Rule] = {
    def onPage[A](f: P => A)(page: Page): Option[A] =
      op(page) map f
    new DynamicRouteB(a => Rule(r.parse, onPage(r.pathFor), (_, p) => onPage(a)(p)))
  }

  def dynamicRouteCT[P <: Page](r: Route[P])(implicit ct: ClassTag[P]): DynamicRouteB[Page, P, Rule] =
    dynamicRouteF(r)(ct.unapply)

  def staticRedirect(r: Route[Unit]): StaticRedirectB[Page, Rule] =
    new StaticRedirectB(a => rewritePathF(r.parse(_) map (_ => a)))

  def dynamicRedirect[A](r: Route[A]): DynamicRedirectB[Page, A, Rule] =
    new DynamicRedirectB(f => rewritePathF(r.parse(_) map f))

  def rewritePath(pf: PartialFunction[Path, Redirect]): Rule =
    rewritePathF(pf.lift)

  def rewritePathF(f: Path => Option[Redirect]): Rule =
    Rule parseOnly f

  def rewritePathR(r: Pattern, f: Matcher => Option[Redirect]): Rule =
    rewritePathF { p =>
      val m = r.matcher(p.value)
      if (m.matches) f(m) else None
    }

  // -------------------------------------------------------------------------------------------------------------------
  // Utilities

  /**
    * Removes the query portion of the URL.
    *
    * e.g. `a/b?c=1` to `a/b`
    */
  def removeQuery: Rule =
    rewritePathR("^(.*?)\\?.*$".r, m => redirectToPath(m group 1)(Redirect.Replace))

  /**
   * A rule that uses a replace-state redirect to remove trailing slashes from route URLs.
   */
  def removeTrailingSlashes: Rule =
    rewritePathR("^(.*?)/+$".r, m => redirectToPath(m group 1)(Redirect.Replace))

  /**
   * A rule that uses a replace-state redirect to remove leading slashes from route URLs.
   */
  def removeLeadingSlashes: Rule =
    rewritePathR("^/+(.*)$".r, m => redirectToPath(m group 1)(Redirect.Replace))

  /**
   * A rule that uses a replace-state redirect to remove leading and trailing slashes from route URLs.
   */
  def trimSlashes: Rule = (
    rewritePathR("^/*(.*?)/+$".r, m => redirectToPath(m group 1)(Redirect.Replace))
    | removeLeadingSlashes)
}
